package edu.shu.styluo.collegeentranceexamination.data.remote.entity;

import java.util.List;

/**
 * 高校排名entity实体类
 * author: styluo
 * date: 2017/4/28 10:29
 * e-mail: shu_jiahuili@foxmail.com
 */

public class WishRankCollege {

    private List<RowsBean> rows;

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 1
         * rank : 1
         * univCode : 10001
         * univName : 北京大学
         * univProvince : 北京
         */

        private int id;
        private String rank;
        private String univCode;
        private String univName;
        private String univProvince;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getUnivCode() {
            return univCode;
        }

        public void setUnivCode(String univCode) {
            this.univCode = univCode;
        }

        public String getUnivName() {
            return univName;
        }

        public void setUnivName(String univName) {
            this.univName = univName;
        }

        public String getUnivProvince() {
            return univProvince;
        }

        public void setUnivProvince(String univProvince) {
            this.univProvince = univProvince;
        }
    }
}
