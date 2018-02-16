package janjira.jiraporn.yonlada.aroirestuarant.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import janjira.jiraporn.yonlada.aroirestuarant.R;
import janjira.jiraporn.yonlada.aroirestuarant.utility.LoadAllJSON;
import janjira.jiraporn.yonlada.aroirestuarant.utility.MyConstanct;

/**
 * Created by TOA on 2/16/2018.
 */

public class FirstFrgment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

           loginController();

    }

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();
                Log.d("test1", "user=" + userString);
                Log.d("test1", "pass=" + passwordString);


//                 Check Space
                if (userString.isEmpty() || passwordString.isEmpty()) {
//                      Have Space
                    myAlert("Please Fill All Blank");

                } else {
//                     No Space
                    try {


                        MyConstanct myConstanct = new MyConstanct();
                        boolean userBoolean = true; //True ==> User False
                        LoadAllJSON loadAllJSON = new LoadAllJSON(getActivity());
                        loadAllJSON.execute(myConstanct.getUrlGetAllUserString());
                        String[] columnStrings = new String[]{"id", "Name", "Password", "Category"};
                        String[] loginStrings = new String[columnStrings.length];


                        String resultJSON = loadAllJSON.get();
                        Log.d("test1", "Json ==>" + resultJSON);

                        JSONArray jsonArray = new JSONArray(resultJSON);

                        for (int i = 0; i < jsonArray.length(); i += 1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString("User"))) {

                                userBoolean = false;
                                for (int i1=0; i1<loginStrings.length; i1+=1) {
                                    loginStrings[i1] = jsonObject.getString(columnStrings[i1]);
                                }   //for
                            }   // if1

                        }   // for

                        if (userBoolean) {
                            myAlert("NO This User in my Database");
                        } else if (!passwordString.equals(loginStrings[3])) {
                            myAlert("Password False");

                        } else {
                            myAlert("Welcome" + loginStrings[1]);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }  //if


            }   //onClick
        });
    }

    private void myAlert(String messageString) {
        Toast.makeText(getActivity(), messageString, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fradment_first, container, false);
        return view;
    }
} //Main Class
