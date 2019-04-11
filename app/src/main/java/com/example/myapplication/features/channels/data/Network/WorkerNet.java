package com.example.myapplication.features.channels.data.Network;

import android.content.Context;

import com.example.myapplication.App;
import com.example.myapplication.features.channels.domain.model.Channel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerNet extends Worker {

    public WorkerNet(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        //ArrayList<String> titles = new ArrayList<>();
        String titles = "";
        ArrayList<String> links = new ArrayList<>();

        String address;
        Exception exception = null;
        URL url;

        List<Channel> channelList = App.getDataBase().getChannelDao().getAllChannelsDead();

        for (int i = 0; i < channelList.size(); i++){

            address = channelList.get(i).getUrl();

            try {

                url = new URL(address);

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xmlPullParser = factory.newPullParser();
                xmlPullParser.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;

                int eventType = xmlPullParser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT){

                    if (eventType == XmlPullParser.START_TAG){

                        if (xmlPullParser.getName().equalsIgnoreCase("item")){
                            insideItem = true;
                        } else {

                            if (xmlPullParser.getName().equalsIgnoreCase("title")){

                                if (insideItem){
                                    //titles.add(xmlPullParser.nextText());
                                    titles = titles.concat(xmlPullParser.nextText() + ",");
                                }
                            }
                            else {

                                if (xmlPullParser.getName().equalsIgnoreCase("link")){

                                    if (insideItem){
                                        links.add(xmlPullParser.nextText());
                                    }
                                }
                            }
                        }
                    } else {

                        if (eventType == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")){
                            insideItem = false;
                        }
                    }
                    eventType = xmlPullParser.next();
                }

            }
            catch (MalformedURLException e){
                exception = e;
            }
            catch (XmlPullParserException e){
                exception = e;
            }
            catch (IOException e){
                exception = e;
            }
            channelList.get(i).setNews(titles);
        }

        if (exception != null){
            return Result.failure();
        } else {
            App.getDataBase().getChannelDao().updateAllChannels(channelList);
            return Result.success();
        }
    }

    private InputStream getInputStream(URL url){
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e){
            return  null;
        }
    }
}
