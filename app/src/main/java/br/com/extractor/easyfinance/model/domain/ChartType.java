package br.com.extractor.easyfinance.model.domain;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.chart.AbstractChart;
import br.com.extractor.easyfinance.chart.ChartBalance;
import br.com.extractor.easyfinance.chart.ChartExpensesByType;
import br.com.extractor.easyfinance.chart.ChartIncomesByType;
import br.com.extractor.easyfinance.chart.ChartRelationIncomeExpenditure;
import br.com.extractor.easyfinance.ui.EasyFinanceApplication;

public enum ChartType {

    BALANCE(0, R.string.chart_balance, new ChartBalance()),
    RELATION_INCOME_EXPENDITURE(1, R.string.chart_relation_income_expenditure, new
            ChartRelationIncomeExpenditure()),
    EXPENSES(3, R.string.chart_expenses_by_type, new ChartExpensesByType()),
    INCOMES(4, R.string.chart_incomes_by_type, new ChartIncomesByType());

    private int key;
    private int titleId;
    private AbstractChart chart;

    ChartType(int key, int titleId, AbstractChart chart) {
        this.key = key;
        this.titleId = titleId;
        this.chart = chart;
    }

    public static ChartType getChartTypeByKey(int key) {
        for (ChartType chartType : ChartType.values()) {
            if (chartType.getKey() == key) {
                return chartType;
            }
        }
        return null;
    }

    public int getTitleId() {
        return titleId;
    }

    public AbstractChart getChart() {
        return chart;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return EasyFinanceApplication.application.getString(getTitleId());
    }

}
