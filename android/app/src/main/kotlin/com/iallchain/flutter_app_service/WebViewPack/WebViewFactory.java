package com.iallchain.flutter_app_service.WebViewPack;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;

import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

public class WebViewFactory extends PlatformViewFactory {
    private WebView wv;

    public WebViewFactory(MessageCodec<Object> createArgsCodec, WebView wv) {
        super(createArgsCodec);
        this.wv = wv;
    }
    @Override
    public PlatformView create(Context context, int i, Object o) {
        return new PlatformView() {
            @Override
            public View getView() {
                return wv;
            }

            @Override
            public void dispose() {

            }
        };
    }
}
