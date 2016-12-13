package dk.sdu.com.democracy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalActivity;
import org.achartengine.GraphicalView;
import org.achartengine.chart.AbstractChart;
import org.achartengine.chart.PieChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import dk.sdu.com.democracy.charts.StatisticsPieChart;

public class StatisticsActivity extends AppCompatActivity {

    private TextView user;
    private TextView description;

    private ImageButton imgBtnUpVote;

    private FrameLayout layout;
    private GraphicalView mView;
    private AbstractChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

        /*layout = (FrameLayout) findViewById(R.id.frame_view);

        int[] colors = new int[] { Color.RED, Color.YELLOW, Color.BLUE };
        DefaultRenderer renderer = buildCategoryRenderer(colors);

        CategorySeries categorySeries = new CategorySeries("Vehicles Chart");
        categorySeries.add("cars ", 30);
        categorySeries.add("trucks", 20);
        categorySeries.add("bikes ", 60);


        mChart = new PieChart(categorySeries, renderer);

        this.mView = new GraphicalView(this, this.mChart);

        layout.addView(this.mView);
    }*/

    /*protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }

        renderer.setPanEnabled(false);
        renderer.setZoomEnabled(false);

        return renderer;
    }*/
}
