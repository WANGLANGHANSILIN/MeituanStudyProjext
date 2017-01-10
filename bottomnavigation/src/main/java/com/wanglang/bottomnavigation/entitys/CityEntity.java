package com.wanglang.bottomnavigation.entitys;

import java.util.ArrayList;

/**
 * Created by wangl on 2016/12/12 0012.
 */

public class CityEntity {
    private String rank;
    private String name;
    private String pinyin;
    private String acronym;


    public CityEntity() {
    }

    public CityEntity(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }


    public static class CityListShowEntity{
        private String letter;
        private ArrayList<String> cityList;

        public CityListShowEntity(String letter) {
            this.letter = letter;
        }

        public ArrayList<String> getCityList() {
            return cityList;
        }

        public void setCityList(ArrayList<String> cityList) {
            this.cityList = cityList;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }
    }
}
