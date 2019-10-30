package org.diiage.dtrqandroid.drivingLessons.recyclerViewAdapter;

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
import org.diiage.dtrqandroid.drivingLessons.NextDrivingLessonsListFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewNextDrivingLessonAdapter extends RecyclerView.Adapter<RecyclerViewNextDrivingLessonAdapter.DrivingLessonHolder> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private List<DrivingLesson> drivingLessonList = new ArrayList<>();
    private DrivingLessonViewModel drivingLessonViewModel;
    UserSessionManager session;
    private Fragment fragment;
    private Context context;
    private Long userId;
    private Consumer onclick;

    public RecyclerViewNextDrivingLessonAdapter(Consumer<DrivingLesson> onClick){
        this.onclick = onClick;
    }

    @NonNull
    @Override
    public DrivingLessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_driving_lesson_item,parent, false);


        return new DrivingLessonHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrivingLessonHolder holder, int position) {
        DrivingLesson currentDrivingLesson = drivingLessonList.get(position);
        holder.textViewDate.setText(holder.textViewDate.getText() + new SimpleDateFormat("dd MMMM yyyy 'à' hh'h'mm").format(currentDrivingLesson.getDate())); // simpledate format
        holder.textViewText.setText(currentDrivingLesson.getText());
        holder.btnInscrire.setOnClickListener(v -> this.onclick.accept(currentDrivingLesson));
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

            btnInscrire = itemView.findViewById(R.id.btnRegister);


        }
    }
}