package com.walhalla.landing.utility;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import com.walhalla.landing.WAppConfig;
import com.walhalla.landing.WebViewAppConfig;

public class DownloadUtility {

    @SuppressLint("ObsoleteSdkInt")
    public static void downloadFile(Context context, String url, String fileName) {
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.HONEYCOMB_MR2) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

            // set download directory
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

            // when downloading music and videos they will be listed in the player
            request.allowScanningByMediaScanner();

            // notify user when download is completed
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            // start download
            DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
        } else {
            try {
                if (url != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(intent);
                }
            } catch (android.content.ActivityNotFoundException e) {
                // can't start activity
            }
        }
    }


    public static boolean isDownloadableFile(String url) {
        int index = url.indexOf("?");
        if (index > -1) {
            url = url.substring(0, index);
        }
        url = url.toLowerCase();
        for (String type : WAppConfig.DOWNLOAD_FILE_TYPES) {
            if (url.endsWith(type)) return true;
        }
        return false;
    }

    public static boolean isLinkInternal(String url) {
        for (String rule : WebViewAppConfig.LINKS_OPENED_IN_INTERNAL_WEBVIEW) {
            if (url.contains(rule)) return true;
        }
        return false;
    }

    public static String getFileName(String url) {
        int index = url.indexOf("?");
        if (index > -1) {
            url = url.substring(0, index);
        }
        url = url.toLowerCase();

        index = url.lastIndexOf("/");
        if (index > -1) {
            return url.substring(index + 1, url.length());
        } else {
            return Long.toString(System.currentTimeMillis());
        }
    }

    public static boolean isLinkExternal(String url) {
        for (String rule : WebViewAppConfig.LINKS_OPENED_IN_EXTERNAL_BROWSER) {
            if (url.contains(rule)) return true;
        }
        return false;
    }

}
