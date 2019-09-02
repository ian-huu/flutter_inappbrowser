package com.pichillilorenzo.flutter_inappbrowser.InAppWebView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import io.flutter.plugin.common.PluginRegistry.ActivityResultListener;

public class InAppActivityResultListener implements ActivityResultListener {
    private InAppWebView webView;

    public InAppActivityResultListener(InAppWebView webView) {
        this.webView = webView;
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case InAppWebChromeClient.FILECHOOSER_RESULTCODE:
            pickPhotoResult(resultCode, data);
            break;
        case InAppWebChromeClient.PHOTOTAKER_RESULTCODE:
            pickPhotoResult(resultCode, data);
            break;
        default:
            break;
        }
        return true;
    }

    private void pickPhotoResult(int resultCode, Intent data) {
        Uri uri = data.getData();
        if (resultCode == Activity.RESULT_CANCELED || uri == null) {
            if (webView.inAppWebChromeClient.mUploadMessage != null) {
                webView.inAppWebChromeClient.mUploadMessage.onReceiveValue(null);
            }
            if (webView.inAppWebChromeClient.mUploadMessageArray != null) {
                webView.inAppWebChromeClient.mUploadMessageArray.onReceiveValue(null);
            }
        } else {
            if (webView.inAppWebChromeClient.mUploadMessage != null) {
                webView.inAppWebChromeClient.mUploadMessage.onReceiveValue(uri);
            }
            if (webView.inAppWebChromeClient.mUploadMessageArray != null) {
                webView.inAppWebChromeClient.mUploadMessageArray.onReceiveValue(new Uri[]{uri});
            }
        }
    }
}