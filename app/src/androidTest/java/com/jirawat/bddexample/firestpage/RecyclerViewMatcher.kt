package com.jirawat.bddexample.firestpage
import android.content.res.Resources;
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View;
import com.jirawat.bddexample.presentation.login.adapter.MemeViewHolder
import com.jirawat.bddexample.presentation.login.adapter.NetworkStateLoad
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;



/**
 * Created by dannyroa on 5/10/15.
 */
object RecyclerViewMatcher{

    fun atPositionViewHolder():Matcher<RecyclerView.ViewHolder>{
        return object : TypeSafeMatcher<RecyclerView.ViewHolder>(){
            override fun describeTo(description: Description?) {

            }

            override fun matchesSafely(view: RecyclerView.ViewHolder): Boolean {
                return when(view){
                    is NetworkStateLoad ->{
                        Log.d("dfdfdfdf","matchesSafely")
                        true}
                    else ->{
                        Log.d("dfdfdfdf","elseeee")
                        false}
                }
            }

        }
    }



    fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Click on a child view with specified id."
            }

            override fun perform(uiController: UiController, view: View) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }

    fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }

}