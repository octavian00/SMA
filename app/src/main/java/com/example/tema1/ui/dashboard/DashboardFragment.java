package com.example.tema1.ui.dashboard;

import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tema1.BottomNavigationExampleActivity;
import com.example.tema1.CarAdapter;
import com.example.tema1.R;
import com.example.tema1.UserRecyclerView.UserAdapter;
import com.example.tema1.database.User;
import com.example.tema1.database.UserDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private RecyclerView user_rv;
    private UserDatabase userDatabase;
    private UserAdapter userAdapter;
    private List<User> userList;
    private Button button;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initializeViews(root);
        GetFromDatabase();
        listener();
        return root;
    }

    private void initializeViews(View view)
    {
        button=view.findViewById(R.id.btn_refresh);
        userList = new ArrayList<>();
        userDatabase = UserDatabase.getInstance(getContext());
        user_rv = view.findViewById(R.id.rv_user);
    }
    private void setRecyclerView(){
        userAdapter = new UserAdapter(userList);
        Log.e("dash","ajung aici");
        Log.e("dash",userList.size()+"");
        user_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        user_rv.setAdapter(userAdapter);
    }

    private void GetFromDatabase(){
        class GetValue extends AsyncTask<Void,Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                userList = userDatabase.userDAO().getAll();
                return new User();
            }

            @Override
            protected void onPostExecute(User testEntity) {
                super.onPostExecute(testEntity);
                setRecyclerView();
            }
        }
        GetValue getValue = new GetValue();
        getValue.execute();
    }
    private void listener(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetFromDatabase();
                setRecyclerView();
            }
        });
    }
}