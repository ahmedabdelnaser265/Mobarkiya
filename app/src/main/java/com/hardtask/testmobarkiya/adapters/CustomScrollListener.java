package com.hardtask.testmobarkiya.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import static com.hardtask.testmobarkiya.HomeActivity.showHideLayout;
import static com.hardtask.testmobarkiya.HomeActivity.slideDownLayout;
import static com.hardtask.testmobarkiya.HomeActivity.slideUpLayout;

/**
 * Created by it_ah on 06/04/2019.
 */

public class CustomScrollListener extends RecyclerView.OnScrollListener {
    public CustomScrollListener() {
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                System.out.println("The RecyclerView is not scrolling");
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                System.out.println("Scrolling now");
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                System.out.println("Scroll Settling");
                break;

        }

    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dx > 0) {
            System.out.println("Scrolled Right");
        } else if (dx < 0) {
            System.out.println("Scrolled Left");
        } else {
            System.out.println("No Horizontal Scrolled");
        }

        if (dy > 0) {
            System.out.println("Scrolled Downwards");

            showHideLayout.setVisibility(View.VISIBLE);
            slideUpLayout(showHideLayout);

        } else if (dy < 0) {
            System.out.println("Scrolled Upwards");


            showHideLayout.setVisibility(View.GONE);
            slideDownLayout(showHideLayout);


        } else {
            System.out.println("No Vertical Scrolled");

//            showHideLayout.setVisibility(View.VISIBLE);
        }
    }
}
