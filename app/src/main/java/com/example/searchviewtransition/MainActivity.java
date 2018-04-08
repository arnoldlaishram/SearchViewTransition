package com.example.searchviewtransition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FrameLayout mainLayout;
    CardView homeSearchCard;
    FrameLayout homeCardContainer;
    FrameLayout searchLayout;
    View alphaView;
    RecyclerView recyclerView;
    private boolean isInSearchView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        isInSearchView = false;

        homeSearchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateToSearchView();
            }
        });

        alphaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateToHomeView();
            }
        });
    }

    private void animateToHomeView() {
        isInSearchView = false;
        TransitionSet transitionSet = new TransitionSet()
                .addTransition(new Fade()
                        .addTarget(alphaView))
                .addTransition(new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeTransform())
                        .addTarget(R.id.homeCard)
                        .addTarget(R.id.searchCard));

        TransitionManager.beginDelayedTransition(mainLayout, transitionSet);

        homeCardContainer.addView(homeSearchCard);
        mainLayout.removeView(searchLayout);
    }

    private void animateToSearchView() {
        isInSearchView = true;
        TransitionSet transitionSet = new TransitionSet()
                .addTransition(new Fade()
                        .addTarget(R.id.alphaView))
                .addTransition(new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeTransform())
                        .addTarget(homeSearchCard)
                        .addTarget(R.id.searchCard));

        TransitionManager.beginDelayedTransition(mainLayout, transitionSet);

        mainLayout.addView(searchLayout);
        homeCardContainer.removeView(homeSearchCard);
    }

    private void initViews() {
        // Setup the toolBar
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        // init the main layout view
        mainLayout = findViewById(R.id.mainLayout);
        homeSearchCard = mainLayout.findViewById(R.id.homeCard);
        homeCardContainer = mainLayout.findViewById(R.id.homeCardContainer);

        // init search layout views
        searchLayout = (FrameLayout) getLayoutInflater().inflate(R.layout.seach_view_layout, null);
        alphaView = searchLayout.findViewById(R.id.alphaView);
        recyclerView = searchLayout.findViewById(R.id.recycler_search_result);

        // init recycler view
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        SearchAdapter searchAdapter = new SearchAdapter(getSearchResults());
        recyclerView.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }

    private List<String> getSearchResults() {
        List<String> searchResults = new ArrayList<>();

        searchResults.add("Shared Element not working");
        searchResults.add("How does transition work?");
        searchResults.add("ConstraintLayout animations");

        return searchResults;
    }

    @Override
    public void onBackPressed() {
        if(isInSearchView) {
            animateToHomeView();
            return;
        }
        super.onBackPressed();
    }
}
