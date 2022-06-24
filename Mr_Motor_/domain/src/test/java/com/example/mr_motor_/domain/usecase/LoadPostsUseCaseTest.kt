package com.example.mr_motor_.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.mr_motor_.domain.models.PostType
import com.example.mr_motor_.domain.repository.PostsRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(23), manifest = "src/main/AndroidManifest.xml", packageName = "com.example.mr_motor_")
class LoadPostsUseCaseTest {
    private val postsRepository = mock<PostsRepository>()
    private lateinit var resultLiveMutable : MutableLiveData<Boolean>

    @Before
    fun doBefore(){
        resultLiveMutable = MutableLiveData<Boolean>()
    }

    @Test
    fun shouldLoadNews(){

        val postType = PostType.NEWS
        Mockito.`when`(postsRepository.loadingPosts(resultLiveMutable = resultLiveMutable, postType = postType)).then {
            changeResult(true)
        }

        val useCase = LoadPostsUseCase(postsRepository = postsRepository)
        useCase.execute(resultLiveMutable = resultLiveMutable, postType = postType)
        val expected = true
        Assert.assertEquals(expected, resultLiveMutable.value)

    }

    private fun changeResult(bool : Boolean){
        resultLiveMutable.value = bool
    }
}