package fr.embaya.apps.magicnumberv10;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txtNumber;
    private Button btnValidate;
    private ProgressBar pbScore;
    private TextView lblResult;
    private TextView lblHistory;

    private int Score;
    private int SearchedValue;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.other_menu :
                init();
                return true;
            case R.id.exit_menu:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNumber = (EditText) findViewById(R.id.txtNumber);
        btnValidate = (Button) findViewById(R.id.btnValidate);
        pbScore = (ProgressBar) findViewById(R.id.pbScore);
        lblResult = (TextView) findViewById(R.id.lblResult);
        lblHistory = (TextView) findViewById(R.id.lblHistory);

        btnValidate.setOnClickListener(btnCompareListener);

        init();

    }

    private void init() {
        Score = 0;
        SearchedValue = 1 + (int) (Math.random() * 100);
        Log.i("Debug ", "Searched value " + SearchedValue);
       pbScore.setProgress(Score);
        lblHistory.setText("");
        lblResult.setText("");
        txtNumber.requestFocus();/**/

    }

    private void congratulation() {
        lblResult.setText(R.string.strCongratulation);
        /*AlertDialog retryAlert = new AlertDialog.Builder(this).create();
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage(getString(R.string.strCongartulations, Score));
        retryAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.strYes), new AlertDialog.OnClickListener() {
            @Override
            public OnClick(DialogInterface dialog, int which) {
                init();
            }
        }); */
        AlertDialog.Builder retryAlert = new AlertDialog.Builder(
                MainActivity.this);
        retryAlert.setTitle(R.string.app_name);
        retryAlert.setMessage(getString(R.string.strCongartulations, Score));
        retryAlert.setPositiveButton(getString(R.string.strYes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        init();
                    }
                });
        retryAlert.setNegativeButton(getString(R.string.strNo),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        finish();
                    }
                });
        retryAlert.show();
    }

    private View.OnClickListener btnCompareListener = new View.OnClickListener() {
        @Override
        public void onClick(View c) {
            String value = txtNumber.getText().toString();
            if (value.equals("")) return;

            lblHistory.append(txtNumber.getText().toString() + "\r\n");
            pbScore.incrementProgressBy(1);
            Score++;
            int enteredData = Integer.parseInt(value);
            if (enteredData == SearchedValue) {
                congratulation();
            } else if (enteredData < SearchedValue) {
                lblResult.setText(R.string.strGreater);
            } else {
                lblResult.setText(R.string.strLower);
            }
            txtNumber.setText("");
        }
    };
}
