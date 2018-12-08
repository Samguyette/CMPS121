package com.cmps121.sam.assignment2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class PhotoListAdapter extends ArrayAdapter<PhotoObject> {
    private static final String TAG = "PhotoListAdapter";
    private Context mContext;
    int mResourse;

    public PhotoListAdapter(Context context, int resource, ArrayList<PhotoObject> objects){
        super(context, resource, objects);      //initalizes photoListAdapter
        mContext = context;
        mResourse = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        setupImageLoader();
        byte[] photoS = getItem(position).getPhoto();

        String titleS = getItem(position).getTitle();
        String idS = getItem(position).getid();
        String urlS = getItem(position).getUrl();

        PhotoObject temp = new PhotoObject(photoS, titleS, idS, urlS);        //creates temp photo object

        LayoutInflater myInflator = LayoutInflater.from(mContext);
        convertView = myInflator.inflate(mResourse, parent, false);

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.image);
        TextView textViewTitle = (TextView) convertView.findViewById(R.id.nameTextView);       //defines textViews
        TextView textViewid = (TextView) convertView.findViewById(R.id.idTextView);

        Bitmap bm = BitmapFactory.decodeByteArray(photoS, 0, photoS.length);


        photoImageView.setImageBitmap(bm);
        textViewTitle.setText(titleS);                    //sets text of textviews pulling from photo objects
        textViewid.setText(idS);

        int defaultImage = mContext.getResources().getIdentifier("https://media.treehugger.com/assets/images/2017/03/giantpanda.jpg.860x0_q70_crop-scale.jpg", null,mContext.getPackageName());

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)                                     //LINES 62-84 ARE FROM UNIVERSAL IMAGE LOADER GITHUB.
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        imageLoader.displayImage(urlS, photoImageView, options);


        return convertView;
    }
    private void setupImageLoader(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)                                              //LINES 62-84 ARE FROM UNIVERSAL IMAGE LOADER GITHUB.
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
    }
}
