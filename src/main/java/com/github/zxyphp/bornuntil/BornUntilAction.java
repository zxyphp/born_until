package com.github.zxyphp.bornuntil;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class BornUntilAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        String input = Messages.showInputDialog(
                project,
                "请输入出生日期（格式：yyyy-MM-dd）",
                "输入出生日期",
                Messages.getQuestionIcon()
        );

        if (input == null || input.trim().isEmpty()) {
            return;
        }

        try {
            LocalDate birthDate = LocalDate.parse(input.trim());
            LocalDate today = LocalDate.now();

            Period age = Period.between(birthDate, today);

            String ageStr = String.format(
                    "%d岁 %d个月 %d天",
                    age.getYears(), age.getMonths(), age.getDays()
            );

            Messages.showInfoMessage(project, "你当前的年龄是：" + ageStr, "年龄计算结果");

        } catch (DateTimeParseException ex) {
            Messages.showErrorDialog(project, "格式错误，请使用 yyyy-MM-dd 格式。", "输入错误");
        }
    }
}
