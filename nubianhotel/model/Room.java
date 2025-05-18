package com.example.nubianhotel.model;

public class Room {
        private int id;
        private String number;
        private String type;
        private RoomStatus status;

        public Room(int id, String number, String type, RoomStatus status) {
            this.id = id;
            this.number = number;
            this.type = type;
            this.status = status;
        }

        public RoomStatus getStatus() {
            return status;
        }

        public void setStatus(RoomStatus status) {
            this.status = status;
        }

        public boolean isAvailable() {
            return status.isAvailable();
        }

        public String getStatusText() {
            return status.getStatus();
        }

        public String getType() {
        return type;
        }


        public int getId() {
        return id;
        }
}


