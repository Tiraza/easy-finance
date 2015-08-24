package br.com.extractor.easyfinance.model.domain;

import br.com.extractor.easyfinance.R;
import br.com.extractor.easyfinance.chart.Panel;
import br.com.extractor.easyfinance.chart.charts.ChartExpensesByType;
import br.com.extractor.easyfinance.chart.charts.ChartIncomesByType;
import br.com.extractor.easyfinance.chart.charts.PanelChartBalance;
import br.com.extractor.easyfinance.chart.charts.PanelExpenseVsIncome;
import br.com.extractor.easyfinance.chart.charts.PanelSimple;
import br.com.extractor.easyfinance.ui.EasyFinanceApplication;

public enum PanelType {

    SIMPLE(-1, R.string.none, new PanelSimple()),
    BALANCE(0, R.string.panel_chart_balance, new PanelChartBalance()),
    RELATION_INCOME_EXPENDITURE(1, R.string.panel_income_vs_expense, new PanelExpenseVsIncome()),
    //EXPENSES(3, R.string.chart_expenses_by_type, new ChartExpensesByType()),
    INCOMES(4, R.string.chart_incomes_by_type, new ChartIncomesByType());

    private int key;
    private int titleId;
    private Panel panel;

    PanelType(int key, int titleId, Panel panel) {
        this.key = key;
        this.titleId = titleId;
        this.panel = panel;
    }

    public static PanelType getChartTypeByKey(int key) {
        for (PanelType panelType : PanelType.values()) {
            if (panelType.getKey() == key) {
                return panelType;
            }
        }
        return null;
    }

    public int getTitleId() {
        return titleId;
    }

    public Panel getPanel() {
        return panel;
    }

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return EasyFinanceApplication.application.getString(getTitleId());
    }

}
