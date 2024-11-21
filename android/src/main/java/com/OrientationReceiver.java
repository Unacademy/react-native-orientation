package com.github.yamill.orientation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class OrientationReceiver extends BroadcastReceiver {
  private ReactApplicationContext ctx;

  // Some comment
  public OrientationReceiver() {

  }
  public OrientationReceiver(ReactApplicationContext context) {
    this.ctx = context;
  }

  @Override
  public void onReceive(Context context, Intent intent) {
    Configuration newConfig = intent.getParcelableExtra("newConfig");
    Log.d("OrientationReceiver", String.valueOf(newConfig.orientation));

    String orientationValue = newConfig.orientation == 1 ? "PORTRAIT" : "LANDSCAPE";

    WritableMap params = Arguments.createMap();
    params.putString("orientation", orientationValue);
    if (ctx.hasActiveCatalystInstance()) {
      ctx.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
        .emit("orientationDidChange", params);
    }
  }
}