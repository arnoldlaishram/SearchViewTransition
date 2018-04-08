# SearchViewTransition
A simple transition when search mode is on. This app uses android Transitions for smooth animation

![The search layout animation](https://github.com/ArnoldLaishram/SearchViewTransition/blob/master/transition.gif)

It basically consist of 2 layouts, **main** and **search view**.

To animate from **main** to **search view**, use this transition

```java

        TransitionSet transitionSet = new TransitionSet()
                .addTransition(new Fade()
                        .addTarget(R.id.alphaView))
                .addTransition(new TransitionSet() // For Shared element from homeCard to searchCard
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeTransform())
                        .addTarget(homeCard)
                        .addTarget(R.id.searchCard));

        TransitionManager.beginDelayedTransition(mainLayout, transitionSet);

        mainLayout.addView(searchLayout);
        homeCardContainer.removeView(homeSearchCard);

```

And to animate from **search view** to **main**, use this transiton

```java

        TransitionSet transitionSet = new TransitionSet()
                .addTransition(new Fade()
                        .addTarget(alphaView))
                .addTransition(new TransitionSet() // For Shared element from searchCard to homeCard
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeTransform())
                        .addTarget(R.id.homeCard)
                        .addTarget(searchCard));

        TransitionManager.beginDelayedTransition(mainLayout, transitionSet);

        homeCardContainer.addView(homeCard);
        mainLayout.removeView(searchLayout);

```

[Inspiration](https://www.youtube.com/watch?v=9Y5cbC5YrOY)
