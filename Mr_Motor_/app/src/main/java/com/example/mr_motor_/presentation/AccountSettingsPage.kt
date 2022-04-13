package com.example.mr_motor_.presentation

import android.Manifest
import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mr_motor_.R
import com.example.mr_motor_.domain.models.login.ApiClient
import com.example.mr_motor_.data.storage.SessionManager
import com.example.mr_motor_.domain.models.SignUpRequest
import com.example.mr_motor_.domain.models.UserResponse
import com.google.android.material.transition.platform.MaterialSharedAxis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class AccountSettingsPage : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var saveButton: ImageButton
    private lateinit var editAvatarButton: ImageButton
    private lateinit var imageAvatar: ImageView

    private var avatarString : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.account_settings_page)

        nameEditText = findViewById(R.id.et_settingsPage_name)
        saveButton = findViewById(R.id.ib_settingsPage_save)
        editAvatarButton = findViewById(R.id.ib_edit_avatar)
        imageAvatar = findViewById(R.id.iv_account_photo)

        editAvatarButton.setOnClickListener {
            if(checkAndRequestPermissions(this)){
                chooseImage(this);
            }
        }

        val sessionManager: SessionManager = SessionManager(this)
        val user: UserResponse? = sessionManager.fetchUser()

        if (user != null) {
            findViewById<TextView>(R.id.tv_name).text = user.name
            findViewById<TextView>(R.id.tv_email).text = user.email
            nameEditText.setText(user.name)

            if(user.avatar.isNotEmpty()){
                var encoded : String = user.avatar.substring(user.avatar.indexOf(',')+1)

                val decodedString: ByteArray = Base64.decode(encoded, Base64.DEFAULT)
                val bitmap =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                imageAvatar.setImageBitmap(bitmap)
            }
        }

        saveButton.setOnClickListener {
            if (user != null && (nameEditText.text.toString() != user?.name || avatarString != null)) {

                Log.d("WORKS", "ffff")
                ApiClient.getApiService().update(
                    SignUpRequest(
                        name = nameEditText.text.toString(),
                        email = user.email,
                        password = "",
                        avatar = (if(avatarString == null){
                            user.avatar
                        }else{
                            avatarString
                        })!!

                    ), sessionManager.fetchAuthToken()
                ).enqueue(object : Callback<UserResponse> {
                    override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                        var toast: Toast = Toast.makeText(
                            this@AccountSettingsPage,
                            "Not successfully changed!",
                            Toast.LENGTH_LONG
                        )
                        toast.show()
                    }

                    override fun onResponse(
                        call: Call<UserResponse>,
                        response: Response<UserResponse>
                    ) {
                        sessionManager.saveUser(response.body()!!)
                        var toast: Toast = Toast.makeText(
                            this@AccountSettingsPage,
                            "Successfully changed!",
                            Toast.LENGTH_LONG
                        )
                        toast.show()
                        finish()
                    }
                })
            }
        }

        val sharedAxisExit = MaterialSharedAxis(MaterialSharedAxis.X, false).apply {
            excludeTarget(R.id.action_bar_container, true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }
        val sharedAxisEnter = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
            excludeTarget(R.id.action_bar_container, true)
            excludeTarget(android.R.id.statusBarBackground, true)
            excludeTarget(android.R.id.navigationBarBackground, true)
        }

        window.exitTransition = sharedAxisExit
        window.enterTransition = sharedAxisEnter

    }


    //HERE STARTS CODE WITH CHANGING IMAGE FOR AVATAR
    val REQUEST_ID_MULTIPLE_PERMISSIONS = 1

    fun checkAndRequestPermissions(context: Activity?): Boolean {
        val WExtstorePermission = ContextCompat.checkSelfPermission(
            context!!,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val cameraPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        )
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                .add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(
                context, listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_MULTIPLE_PERMISSIONS
            )
            return false
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_ID_MULTIPLE_PERMISSIONS -> if (ContextCompat.checkSelfPermission(
                    this@AccountSettingsPage,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT
                )
                    .show()
            } else if (ContextCompat.checkSelfPermission(
                    this@AccountSettingsPage,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(
                    applicationContext,
                    "FlagUp Requires Access to Your Storage.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                chooseImage(this@AccountSettingsPage)
            }
        }
    }

    private fun chooseImage(context: Context) {
        val optionsMenu = arrayOf<CharSequence>(
            "Take Photo",
            "Choose from Gallery",
            "Exit"
        ) // create a menuOption Array
        // create a dialog for showing the optionsMenu
        val builder  = AlertDialog.Builder(context)
        // set the items in builder
        builder.setItems(optionsMenu,
            DialogInterface.OnClickListener { dialogInterface, i ->
                if (optionsMenu[i] == "Take Photo") {
                    // Open the camera and get the photo
                    val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(takePicture, 0)
                } else if (optionsMenu[i] == "Choose from Gallery") {
                    // choose from  external storage
                    val pickPhoto =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(pickPhoto, 1)
                } else if (optionsMenu[i] == "Exit") {
                    dialogInterface.dismiss()
                }
            })
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_CANCELED) {
            Log.d("REQUESTCODE", requestCode.toString())

            when (requestCode) {
                0 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage = data.extras!!["data"] as Bitmap?
                    imageAvatar.setImageBitmap(selectedImage)

                    //making string for uploading avatar on server
                    val baos = ByteArrayOutputStream()
                    selectedImage?.compress(
                        Bitmap.CompressFormat.PNG,
                        100,
                        baos
                    ) //selectedImage is the bitmap object
                    val b: ByteArray = baos.toByteArray()
                    avatarString = "data:image/jpeg;base64," + Base64.encodeToString(b, Base64.DEFAULT)
                }
                1 -> if (resultCode == RESULT_OK && data != null) {
                    val selectedImage: Uri? = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedImage != null) {
                        val cursor: Cursor? =
                            contentResolver.query(selectedImage, filePathColumn, null, null, null)
                        if (cursor != null) {
                            cursor.moveToFirst()
                            val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                            val picturePath: String = cursor.getString(columnIndex)
                            Log.d("PICTURE_PATH", picturePath)
                            val img: Bitmap = BitmapFactory.decodeFile(picturePath)
                            imageAvatar.setImageBitmap(img)
                            cursor.close()

                            //making string for uploading avatar on server
                            val baos = ByteArrayOutputStream()
                            img.compress(
                                Bitmap.CompressFormat.PNG,
                                100,
                                baos
                            ) //img is the bitmap object
                            val b: ByteArray = baos.toByteArray()
                            avatarString = if(picturePath.endsWith(".jpeg") || picturePath.endsWith(".jpg")){
                                "data:image/jpeg;base64," + Base64.encodeToString(b, Base64.DEFAULT)
                            } else{
                                "data:image/png;base64," + Base64.encodeToString(b, Base64.DEFAULT)
                            }

                        }
                    }
                }
            }
        }
    }

    companion object {
        fun start(caller: Activity) {
            val intent = Intent(caller, AccountSettingsPage::class.java)
            val activityOptions = ActivityOptions.makeSceneTransitionAnimation(caller)
            caller.startActivity(intent, activityOptions.toBundle())
        }
    }
}