package room.gaming.egamingroom.helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.view.View;


import room.gaming.egamingroom.models.MyApiResult;

public class MyApiRequest {
    public static <T> void  Get(final Activity activity, final String urlAction, final MyCallback<T> myCallback, boolean isList){
        new AsyncTask<Void, Void, MyApiResult>() {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(activity,"Loading", "Wait for data");
            }

            @Override
            protected MyApiResult doInBackground(Void... voids) {
                MyApiResult result = MyUrlConnection.Get(MyConfig.BaseUrl + urlAction);

                return  result;
            }
            @Override
            protected void onPostExecute(MyApiResult x) {
                progressDialog.dismiss();

                if(x.isException) {
                    Snackbar snackbar;
                    View parentLayout = activity.findViewById(android.R.id.content);
                    if(x.resultCode == 0) {
                        snackbar = Snackbar.make(parentLayout, "Error in communication with server", Snackbar.LENGTH_LONG);
                    } else {
                        snackbar = Snackbar.make(parentLayout, "Error: " + x.errorMessage, Snackbar.LENGTH_LONG);
                    }
                    snackbar.setAction("Try again", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MyApiRequest.Get(activity, urlAction, myCallback, isList);
                        }
                    });
                    snackbar.show();
                } else {
                    T temp;
                    try {
                        if (isList) {
                            temp = MyGson.build().fromJson(x.value, myCallback.GetCustomType());
                        } else {
                            temp = MyGson.build().fromJson(x.value, myCallback.getGenericType());
                        }
                        myCallback.Run(temp);
                    }catch (Exception e) {
                        View parentLayout = activity.findViewById(android.R.id.content);
                        Snackbar.make(parentLayout, "Error: " + x.errorMessage, Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        }.execute();
    }
}
/*public class MyApiRequest {
    public static <T> void  Get(final Activity activity, final String urlAction, final MyCallback<T> myCallback, boolean isList){
        new AsyncTask<Void, Void, MyApiResult>() {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                progressDialog = ProgressDialog.show(activity,"Loading", "Wait for data");
            }

            @Override
            protected MyApiResult doInBackground(Void... voids) {
                MyApiResult result = MyUrlConnection.Get(MyConfig.BaseUrl + urlAction);

               return  result;
            }
            @Override
            protected void onPostExecute(MyApiResult x) {
                progressDialog.dismiss();

                if(x.isException) {
                    Snackbar snackbar;
                     View parentLayout = activity.findViewById(android.R.id.content);
                if(x.resultCode == 0) {
                    snackbar = Snackbar.make(parentLayout, "Error in communication with server", Snackbar.LENGTH_LONG);
                } else {
                    snackbar = Snackbar.make(parentLayout, "Error: " + x.errorMessage, Snackbar.LENGTH_LONG);
                }
                snackbar.setAction("Try again", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       MyApiRequest.Get(activity, urlAction, myCallback, isList);
                    }
                });
                snackbar.show();
                } else {
                     T temp;
                     try {
                         if (isList) {
                             temp = MyGson.build().fromJson(x.value, myCallback.GetCustomType());
                         } else {
                             temp = MyGson.build().fromJson(x.value, myCallback.getGenericType());
                         }
                         myCallback.Run(temp);
                     }catch (Exception e) {
                         View parentLayout = activity.findViewById(android.R.id.content);
                         Snackbar.make(parentLayout, "Error: " + x.errorMessage, Snackbar.LENGTH_LONG).show();
                     }
                 }
            }
        }.execute();
    }
}*/
