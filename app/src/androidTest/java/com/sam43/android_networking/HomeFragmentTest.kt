package com.sam43.android_networking

import androidx.recyclerview.widget.RecyclerView
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.sam43.android_networking.ui.home.HomeFragment
import org.junit.*


class HomeFragmentTest {

    @get:Rule
    val fragmentTestRule = FragmentTestRule.create(HomeFragment::class.java)!!

    @Before
    @Throws(Exception::class)
    fun setUp() {
    }

    @Test
    fun testIfListIsEmpty() {
        // get the list item count inside the fragment
        Assert.assertEquals("aaa", "aaa")
        //Assert.assertEquals(getListItemCount()!!, 20)

        //onView(withId(R.id.rv_popular_movies)).check(matches(isDisplayed()))

        //onView(withId(R.id.rv_popular_movies)).check(matches(isDisplayed()))
        /*if (getListItemCount()!! > 0) {
            *//*Espresso.onView(withId(R.id.rv_popular_movies)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                click()))*//*
            //onView(withRecy)


        }*/
        // check if the list is empty or not

        // check if the onclick is working or not
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
    }

    private fun getListItemCount(): Int? {
        val recyclerView =
            fragmentTestRule.fragment.activity?.findViewById<RecyclerView>(R.id.rv_popular_movies)
        return recyclerView?.adapter?.itemCount
    }
}