package net.ezmovil.betterflytest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import net.ezmovil.betterflytest.info.infoACKS;
import net.ezmovil.betterflytest.info.infoContext;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private static AlbumAdapter2 adapter;
    private List<Album2> albumList;

    public static ArrayList<String> arrayName = new ArrayList<String>();
    public static ArrayList<String> arrayImages = new ArrayList<String>();
    public static ArrayList<Integer> arrayEpisodes = new ArrayList<Integer>();
    public static ArrayList<Bitmap> arrayBitmap = new ArrayList<Bitmap>();
    public ImageView imageView;

    static Album2 a;

    Bitmap b;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        infoContext _infoContext = new infoContext();
        _infoContext.set_context(context);

        imageView = (ImageView) findViewById(R.id.thumbnail);

        final View parentLayout = findViewById(android.R.id.content);
        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumAdapter2(this, albumList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        try {

            /*Bitmap bitmap = DownloadImage("https://rickandmortyapi.com/api/character/avatar/59.jpeg");
            ImageView img = (ImageView) findViewById(R.id.thumbnail);
            img.setImageBitmap(bitmap);*/

            Glide.with(this).load(R.drawable.ic_juangomez).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

       //a = new Album2("Oyta", 12, "puta"); // R.drawable.ic_juangomez);
       //albumList.add(a);


        apiCharacters task1 = new apiCharacters();
        //apiImages task2 = new apiImages();

        final Thread t1 = new Thread(task1, "Thread - 1");

        // now, let's start all three threads
        t1.start();

        try {
            t1.join();
            if (!t1.isAlive()){
                infoACKS _infoACKS = new infoACKS();
                if (_infoACKS.getToken()!=null){
                    prepareAlbums();
                    for (int i = 0; i < arrayImages.size(); i++) {
                        a = new Album2(arrayName.get(i), arrayEpisodes.get(i), arrayImages.get(i));
                        albumList.add(a);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private static void prepareAlbums() {

        infoACKS _infoACKS = new infoACKS();
        String _token = _infoACKS.getToken();

        try {

            JSONObject obj = new JSONObject(_token);

            for (int i = 0; i < obj.getJSONArray("results").length(); i++) {
                String item = obj.getJSONArray("results").get(i).toString();

                JSONObject jObject = new JSONObject(item);

                arrayName.add(jObject.get("name").toString());
                arrayImages.add(jObject.get("image").toString());
                arrayEpisodes.add(jObject.getJSONArray("episode").length());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } catch (Throwable tx) {
            Log.e("My App", "Could not parse malformed JSON: \"" + _token + "\"");
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private static class apiCharacters implements Runnable {
        private Thread predecessor;

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Started");

            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet("https://rickandmortyapi.com/api/character"));
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else {
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
                e.printStackTrace();
            } catch (IOException e) {
                //TODO Handle problems..
                e.printStackTrace();
            }

            infoACKS _infoACKS = new infoACKS();
            _infoACKS.setAck(responseString);

            System.out.println(Thread.currentThread().getName() + " Finished");
        }

        public void setPredecessor(Thread t) {
            this.predecessor = t;
        }
    }

}

