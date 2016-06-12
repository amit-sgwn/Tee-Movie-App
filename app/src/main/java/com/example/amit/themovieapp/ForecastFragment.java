package com.example.amit.themovieapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class ForecastFragment extends Fragment {

    ImageView imageView;
    View view;
    GridView gridView;
    String moviesJsonStr = null;
    static String orderBy;
    ArrayList<String> movieIdArray = new ArrayList<String>();

    public ForecastFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.fragment_forecast, container, false);


        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle sorting
        int id = item.getItemId();
        switch (id) {
            case R.id.action_sort_p:
                FatchImage fatchImage = new FatchImage();
                ForecastFragment.orderBy="popularity";
                fatchImage.execute();
                break;
            case R.id.action_sort_r:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public class FatchImage extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
        }

        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            final String FATCH_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
            final String SORY_BY = "sort_by";
            final String APPID_PARAM = "api_key";
            final String OPEN_MOVIE_API_KEY = "";

            Uri builtUri = Uri.parse(FATCH_BASE_URL).buildUpon()
                    .appendQueryParameter(SORY_BY, orderBy)
                    .appendQueryParameter(APPID_PARAM, OPEN_MOVIE_API_KEY)
                    .build();

            Log.e("url", builtUri.toString());

            try {
                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                } else {
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }
                    if (buffer.length() == 0) {
                        return null;
                    }
                    return moviesJsonStr = buffer.toString();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
            return null;
        }

    }
}
