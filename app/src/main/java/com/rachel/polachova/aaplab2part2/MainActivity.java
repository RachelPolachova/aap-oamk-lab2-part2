package com.rachel.polachova.aaplab2part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	Button addButton;
	Button editButton;
	Button removeButton;
	EditText editText;
	ListView listView;

	ArrayList<String> countries = new ArrayList<>();
	ArrayAdapter arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupUI();
		setupListView();
		setupAddButton();
		setupRemoveButton();
	}

	void setupUI() {
		LinearLayout parent = new LinearLayout(this);
		parent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		parent.setOrientation(LinearLayout.VERTICAL);

		LinearLayout btnLayout = new LinearLayout(this);
		addButton = new Button(this);
		addButton.setText("add");
		editButton = new Button(this);
		editButton.setText("edit");
		removeButton = new Button(this);
		removeButton.setText("remove");

		btnLayout.addView(addButton);
		btnLayout.addView(editButton);
		btnLayout.addView(removeButton);
		btnLayout.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
		btnLayout.setGravity(Gravity.CENTER);

		editText = new EditText(this);
		listView = new ListView(this);

		parent.addView(btnLayout);
		parent.addView(editText);
		parent.addView(listView);
		setContentView(parent);
	}

	void setupListView() {
		arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
		listView.setAdapter(arrayAdapter);
	}

	void setupAddButton() {
		addButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = editText.getText().toString();
				if (textIsValid(text)) {
					countries.add(text);
					updateEditText();
					arrayAdapter.notifyDataSetChanged();
				}
			}
		});
	}

	void setupRemoveButton() {
		removeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String text = editText.getText().toString();
				if (textIsValid(text)) {
					if (countries.contains(text)) {
						countries.remove(text);
						updateEditText();
						arrayAdapter.notifyDataSetChanged();
					}
				}
			}
		});
	}

	void updateEditText() {
		editText.setText("");
	}

	Boolean textIsValid(String text) {
		return (text != null && text.length() > 0);
	}

}
