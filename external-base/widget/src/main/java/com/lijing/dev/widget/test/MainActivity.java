package com.lijing.dev.widget.test;

import android.support.v7.app.AppCompatActivity;

/**
 * @author lijing
 */
public class MainActivity extends AppCompatActivity {


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        RecyclerView recyclerView = findViewById(R.id.rv_main);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        MainAdapter adapter = new MainAdapter(R.layout.widget_main_item, null);
//        recyclerView.setAdapter(adapter);
//        ArrayList<String> strings = new ArrayList<>(100);
//        for (int i = 0; i < 100; i++) {
//            strings.add("item" + i);
//        }
//        adapter.addItems(strings);
//        adapter.addOnItemClickListener((viewHolder, view, position) -> {
//            adapter.getData().remove(position);
//            adapter.notifyItemRemoved(position);
//        });
//    }
//
//    private class MainAdapter extends BaseCommonAdapter<String, BaseViewHolder> {
//
//        private String TAG = "MainAdapter";
//
//        public MainAdapter(int layoutId, @Nullable List<String> data) {
//            super(layoutId, data);
//        }
//
//        @Override
//        protected void convert(BaseViewHolder helper, String item, int position) {
//            helper.setText(R.id.tv_title, item);
//        }
//
//        @NonNull
//        @Override
//        public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            BaseViewHolder baseViewHolder = super.onCreateViewHolder(viewGroup, i);
//            Log.i(TAG, "onCreateViewHolder:" + baseViewHolder.toString());
//            return baseViewHolder;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {
//            Log.i(TAG, "onBindViewHolder: " + baseViewHolder.toString() + "at index :" + i);
//            super.onBindViewHolder(baseViewHolder, i);
//        }
//    }
}
