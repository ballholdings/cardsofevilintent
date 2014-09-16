package holdings.ball.cardsofevilintent;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.ToggleButton;


public class Login extends Activity {

    public static boolean host_playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new SplashFragment())
                    .commit();
        }
        host_playing = false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SplashFragment extends Fragment {

        private Button host_button;
        private Button join_button;

        public SplashFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_splash, container, false);
            host_button = (Button) rootView.findViewById(R.id.host_button);
            host_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new HostFragment(), "HostFragment")
                            .commit();
                }
            });
            join_button = (Button) rootView.findViewById(R.id.join_button);
            join_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, new JoinFragment(), "JoinFragment")
                            .commit();
                }
            });
            return rootView;
        }
    }

    public static class JoinFragment extends Fragment {
        public JoinFragment() {
        }
            @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
           View rootView = inflater.inflate(R.layout.fragment_join, container, false);
           return rootView;
        }
    }
    public static class HostFragment extends Fragment {

        Switch playing_toggle;

        public HostFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_host, container, false);

            playing_toggle = (Switch) rootView.findViewById(R.id.switch_playing);

            return rootView;
        }

        public void onToggleClicked(View view){
            // Is the toggle on?
            boolean on = ((ToggleButton) view).isChecked();

            if (on) {
                host_playing = false;
            } else {
                host_playing = true;
            }
        }
    }
}
