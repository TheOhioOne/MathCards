package com.example.android.mathcards;

import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG_CARD_BACK = "tagCardBack";
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private boolean mShowingBack = false;
    private MathPresenter presenter = new MathPresenter();
    private Mode mode = Mode.add;
    private int topNumber;
    private int bottomNumber;
    private int result;

    private void loadData(){
        topNumber = presenter.getRandomNumber();
        bottomNumber = presenter.getRandomNumber();
        if (Mode.divide == mode){
            result = topNumber;
            topNumber = presenter.createNewNumbers(topNumber,bottomNumber,mode);
        }
        else {
            result = presenter.createNewNumbers(topNumber,bottomNumber,mode);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       if (savedInstanceState == null) {
            loadData();
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, CardFrontFragment.createInstance(topNumber, bottomNumber, mode.getOperator()), TAG_CARD_BACK)
                    .commit();
            getFragmentManager().executePendingTransactions();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Addition) {
            mode = Mode.add;
        } else if (id == R.id.nav_Subtraction) {
            mode = Mode.subtract;
        } else if (id == R.id.nav_Multiplication) {
            mode = Mode.multiply;
        } else if (id == R.id.nav_Division) {
            mode = Mode.divide;
        } else if (id == R.id.nav_Squares) {
            mode = Mode.square;
        }

        showNewFrontCard();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showNewFrontCard() {
        loadData();
        ((CardFrontFragment)getFragmentManager().findFragmentByTag(TAG_CARD_BACK)).setFrontCardValues(topNumber, bottomNumber, mode.getOperator());
        mShowingBack = false;
    }

    public void flipCard(View view) {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            getFragmentManager().executePendingTransactions(); // finish transaction before setting new values
            showNewFrontCard();
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        getFragmentManager()
                .beginTransaction()

                        // Replace the default fragment animations with animator resources
                        // representing rotations when switching to the back of the card, as
                        // well as animator resources representing rotations when flipping
                        // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                        // Replace any fragments currently in the container view with a
                        // fragment representing the next page (indicated by the
                        // just-incremented currentPage variable).
                .replace(R.id.container, CardBackFragment.createInstance(result))

                        // Add this transaction to the back stack, allowing users to press
                        // Back to get to the front of the card.
                .addToBackStack(null)

                        // Commit the transaction.
                .commit();
    }
}
