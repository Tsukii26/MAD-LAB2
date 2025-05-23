package com.example.nysreynit_lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ExpenseListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;
    private List<Expense> expenseList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data
        expenseList = new ArrayList<>();
        expenseList.add(new Expense(25.00, "USD", "2025-05-23", "Food", "Lunch"));
        expenseList.add(new Expense(50.00, "USD", "2025-05-22", "Transport", "Taxi to work"));

        adapter = new ExpenseAdapter(expenseList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
