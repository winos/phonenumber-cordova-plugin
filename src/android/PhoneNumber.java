package com.skyguard.plugin;

/**
 * This class obtain the Phone Number from device
 *
 * @author dawinos@gmail.com
 * @copy skyguard
 */
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.telephony.TelephonyManager;

public class PhoneNumber extends CordovaPlugin
{
  public static final String ACTION_GET = "get";
  public static final String ACTION_INVALID = "Invalid action";

  private String checkValue(String str) {
       if ((str == null) || (str.length() == 0)) {
           return "\"TM.ERROR\"";
       }

       return "\"" + str + "\"";
  }

  private String getTelephone(TelephonyManager tm) {
      String str = "{";

      if (tm != null) {
          str = "\"deviceID\": " + checkValue(tm.getDeviceId()) + ","
                  + "\"phoneNo\": " + checkValue(tm.getLine1Number()) + ","
                  + "\"netCountry\": " + checkValue(tm.getNetworkCountryIso()) + ","
                  + "\"netName\": " + checkValue(tm.getNetworkOperatorName()) + ","
                  + "\"simNo\": " + checkValue(tm.getSimSerialNumber()) + ","
                  + "\"simCountry\": " + checkValue(tm.getSimCountryIso()) + ","
                  + "\"simName\": " + checkValue(tm.getSimOperatorName());
      }
      str += "}";

      return str;
  }

  public boolean execute(
    String action,
    JSONArray jargs,
    CallbackContext callbackContext) {
      try {

        if (ACTION_GET.equals(action)) {
          final JSONObject args = jargs.getJSONObject(0);

          TelephonyManager tm = (TelephonyManager) this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
          String result = getTelephone(tm);
          if (result != null) {
            callbackContext.success(result);
            return true;
          }
          callbackContext.error(ACTION_INVALID);
        }
      } catch (Exception e) {
        callbackContext.error(e.getMessage());
        return false;
      }
    }
}
