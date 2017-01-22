package com.doozyinfosystem.sample.doozyinfosystem;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class FilesActivity extends AppCompatActivity {

    private static final String FILE_NAME = "Test_File.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonDelete = (Button) findViewById(R.id.delete_button);


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(getFilesDir(), FILE_NAME);
                if (file.exists()) {
                    deleteFile(FILE_NAME);
                    Toast.makeText(getBaseContext(), "File Deleted :" + getFilesDir().toString() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
                } else


                {
                    Toast.makeText(getBaseContext(), "File not found :" + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
                }

            }
        });

        Button buttonCreate = (Button) findViewById(R.id.create_button);
        buttonCreate.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                boolean b = JSONHelper.exportJson(getBaseContext(), DataProvider.getCartItems());
                if (b)
                    Toast.makeText(getBaseContext(), "Exported to JSON :" + Environment.getExternalStorageDirectory() + "/" + JSONHelper.FILE_NAME, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getBaseContext(), "Export Failed :" + Environment.getExternalStorageDirectory() + "/" + JSONHelper.FILE_NAME, Toast.LENGTH_LONG).show();

            }
        });
        Button buttonRead = (Button) findViewById(R.id.read_button);
        buttonRead.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                List<Product> list = new ArrayList<>();
                list = JSONHelper.importJson();

                TextView textView= (TextView) findViewById(R.id.content_file);
                for(Product p:list)
                    textView.append(p.getName()+"\t\t\t"+p.getPrice()+"\n");
            }
        });

    }


}


