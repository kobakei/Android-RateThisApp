/*
 * Copyright 2013 Keisuke Kobayashi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
package com.kobakei.ratethisapp.sample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kobakei.ratethisapp.RateThisApp;

/**
 * Sample application of RateThisApp
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set custom criteria (optional)
        RateThisApp.init(new RateThisApp.Config(3, 5));

        // Set callback (optional)
        RateThisApp.setCallback(new RateThisApp.Callback() {
            @Override
            public void onYesClicked() {
                Toast.makeText(MainActivity.this, "Yes event", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNoClicked() {
                Toast.makeText(MainActivity.this, "No event", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelClicked() {
                Toast.makeText(MainActivity.this, "Cancel event", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        // Set custom title and message
        RateThisApp.Config config = new RateThisApp.Config(3, 5);
        config.setTitle(R.string.hello_world);
        config.setMessage(R.string.hello_world);
        RateThisApp.init(config);
        */

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show rating dialog explicitly.
                RateThisApp.showRateDialog(MainActivity.this);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show rating dialog explicitly.
                RateThisApp.showRateDialog(MainActivity.this, R.style.MyAlertDialogStyle2);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RateThisApp.stopRateDialog(MainActivity.this);
            }
        });

        // Monitor launch times and interval from installation
        RateThisApp.onCreate(this);
        // Show a dialog if criteria is satisfied
        RateThisApp.showRateDialogIfNeeded(this);
    }

}
