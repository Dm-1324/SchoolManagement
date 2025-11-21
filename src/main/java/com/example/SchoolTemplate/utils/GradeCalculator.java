package com.example.SchoolTemplate.utils;

import com.example.SchoolTemplate.enums.Grade;

public class GradeCalculator {

    public static Grade calculateGrade(Long mark) {

        if (mark < 0 || mark > 100) {
            return Grade.FAIL;
        }

        if (mark >= 91) {
            return Grade.A1;
        } else if (mark >= 81) {
            return Grade.A2;
        } else if (mark >= 71) {
            return Grade.B1;
        } else if (mark >= 61) {
            return Grade.B2;
        } else if (mark >= 51) {
            return Grade.C1;
        } else if (mark >= 41) {
            return Grade.C2;
        } else {

            return Grade.FAIL;
        }
    }
}