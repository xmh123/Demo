package com.example.fragmenttabhost;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //创建一个fragment的数组,方便以后更改一级界面中fragment的个数,
    // 如需修改在这里添加或删除即可
    private Class[] fragments = {BlankFragment.class, BlankFragment2.class, BlankFragment.class, BlankFragment2.class};
    //为框架底部设置选择器的图片数组,如需添加新的模块,再添加状态选择器即可
    //注意:这里的数组中放的是drawable文件中的xml文件
    private int[] imgSelects = {R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, R.mipmap.ic_launcher_round};

    //设置图片底下对应的文字
    private String[] titles = {"首页", "分类", "第三项", "第四项"};
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件,只需要初始化TabHost即可
        initView();
        //使用此方法为TabHost添加fragment
        //第一个参数为上下文,第二个参数为fragment的管理器(有support是v4包下的,可支持低版本)
        // 第三个参数是写死的(布局中碎片的id)
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
        tabHost.setup(MainActivity.this, getSupportFragmentManager(), android.R.id.tabcontent);
        //使用for循环为每一个fragment添加子标签,根据fragment集合中的个数动态添加
        for (int i = 0; i < fragments.length; i++) {
            //把一个布局的XML资源转换为一个view的对象,第一个参数就是layout,第二个设置为空
            View view = getLayoutInflater().inflate(R.layout.item_table, null);
            //进行控件查找,将xml的控件初始化
            ImageView imgShow = (ImageView) view.findViewById(R.id.imgShow);
            TextView txtShow = (TextView) view.findViewById(R.id.txtShow);
            //从对应的集合中拿到图片资源
            imgShow.setImageResource(imgSelects[i]);
            //从对应的集合中拿到文本资源
            txtShow.setText(titles[i]);
            //使用fragmentTableHost去添加子标签的核心代码
            //第一个参数为tabHost.newTabSpec(""+i)为每个子标签添加标识,并添加在底部view
            //第二个参数为动态加载的fragment对象即合理的从数组中拿fragment
            //第三个参数为空

            tabHost.addTab(tabHost.newTabSpec("" + i).setIndicator(view), fragments[i], null);
        }

    }


    private void initView() {
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
    }
}
