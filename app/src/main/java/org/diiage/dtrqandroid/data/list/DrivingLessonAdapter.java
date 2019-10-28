package org.diiage.dtrqandroid.data.list;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.diiage.dtrqandroid.R;
import org.diiage.dtrqandroid.data.db.entity.DrivingLesson;
import org.diiage.dtrqandroid.data.db.viewmodel.DrivingLessonViewModel;
import org.diiage.dtrqandroid.data.userManagement.UserSessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class DrivingLessonAdapter extends RecyclerView.Adapter<DrivingLessonAdapter.DrivingLessonHolder> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private List<DrivingLesson> drivingLessonList = new ArrayList<>();
    private DrivingLessonViewModel drivingLessonViewModel;
    UserSessionManager session;
    private Fragment fragment;
    private Context context;
    private Long userId;

    public DrivingLessonAdapter(Fragment fragment, Context context){
        fragment = fragment;
        context = context;
        session = new UserSessionManager(context);
        userId = session.getUserId();
    }

    @NonNull
    @Override
    public DrivingLessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.driving_lesson_item ,parent, false);


        return new DrivingLessonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrivingLessonHolder holder, int position) {
        DrivingLesson currentDrivingLesson = drivingLessonList.get(position);
        holder.textViewDate.setText(holder.textViewDate.getText() + new SimpleDateFormat("dd MMMM yyyy 'à' hh'h'mm").format(currentDrivingLesson.getDate())); // simpledate format
        holder.textViewText.setText(currentDrivingLesson.getText());


        holder.btnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vi) {
                String test ="test";
                final AlertDialog.Builder builder = new AlertDialog.Builder(vi.getContext());
                builder.setTitle("Inscription")
                        .setMessage("Voulez-vous vous inscrire?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO retrieve id and update the driving lesson with the user id
                                /*
                                drivingLessonViewModel = ViewModelProviders.of(, viewModelFactory).get(DrivingLessonViewModel.class);
                                drivingLessonViewModel.inscription(userId, drivingLessonList.get(position).getDrivingLessonId());*/
                                Toast.makeText(vi.getContext(),"Inscription réussie" + drivingLessonList.get(position).drivingLessonId, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                                Toast.makeText(vi.getContext(), "Inscription annulée", Toast.LENGTH_SHORT).show();
                            }
                        });

                builder.create().show();


            }
        });
    }

    public void setDrivingLessons(List<DrivingLesson> drivingLessons){
        this.drivingLessonList = drivingLessons;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return drivingLessonList.size();
    }

    class DrivingLessonHolder extends RecyclerView.ViewHolder{
        private TextView textViewDate;
        private TextView textViewText;
        private Button btnInscrire;

        public DrivingLessonHolder(View itemView){
            super(itemView);
            textViewDate = itemView.findViewById(R.id.date_lesson);
            textViewText = itemView.findViewById(R.id.text_driving);
            btnInscrire = itemView.findViewById(R.id.btnInscrire);


        }
    }
}
