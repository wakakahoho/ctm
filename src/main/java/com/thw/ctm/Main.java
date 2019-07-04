package com.thw.ctm;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author duanxiang 2019/7/1 14:37
 **/
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("1",60));
        nodes.add(new Node("2",45));
        nodes.add(new Node("3",30));
        nodes.add(new Node("4",45));
        nodes.add(new Node("5",45));
        nodes.add(new Node("6",5));
        nodes.add(new Node("7",60));
        nodes.add(new Node("8",45));
        nodes.add(new Node("9",30));
        nodes.add(new Node("10",30));
        nodes.add(new Node("11",45));
        nodes.add(new Node("12",60));
        nodes.add(new Node("13",60));
        nodes.add(new Node("14",45));
        nodes.add(new Node("15",30));
        nodes.add(new Node("16",30));
        nodes.add(new Node("17",60));
        nodes.add(new Node("18",30));
        nodes.add(new Node("19",30));
        /**
         * 1.Writing Fast Tests Against Enterprise Rails 60min
         * 2.Overdoing it in Python 45min
         * 3.Lua for the Masses 30min
         * 4.Ruby Errors from Mismatched Gem Versions 45min
         * 5.Common Ruby Errors 45min
         * 6.Rails for Python Developers lightning
         * 7.Communicating Over Distance 60min
         * 8.Accounting-Driven Development 45min
         * 9.Woah 30min
         * 10.Sit Down and Write 30min
         * 11.Pair Programming vs Noise 45min
         * 12.Rails Magic 60min
         * 13.Ruby on Rails: Why We Should Move On 60min
         * 14.Clojure Ate Scala (on my project) 45min
         * 15.Programming in the Boondocks of Seattle 30min
         * 16.Ruby vs. Clojure for Back-End Development 30min
         * 17.Ruby on Rails Legacy App Maintenance 60min
         * 18.A World Without HackerNews 30min
         * 19.User Interface CSS in Rails Apps 30min
         *
         *
         * 1、 09:00AM Writing Fast Tests Against Enterprise Rails 60min
         * 2、 10:00AM Overdoing it in Python 45min
         * 3、 10:45AM Lua for the Masses 30min
         * 4、 11:15AM Ruby Errors from Mismatched Gem Versions 45min
         * 5、 12:00PM Lunch
         * 6、 01:00PM Ruby on Rails: Why We Should Move On 60min
         * 7、 02:00PM Common Ruby Errors 45min
         * 8、 02:45PM Pair Programming vs Noise 45min
         * 9、 03:30PM Programming in the Boondocks of Seattle 30min
         * 10、 04:00PM Ruby vs. Clojure for Back-End Development 30min
         * 11、 04:30PM User Interface CSS in Rails Apps 30min
         * 12、05:00PM Networking Event
         *
         * 13 09:00AM Communicating Over Distance 60min
         * 14 10:00AM Rails Magic 60min
         * 15 11:00AM Woah 30min
         * 16 11:30AM Sit Down and Write 30min
         * 17 12:00PM Lunch
         * 18 01:00PM Accounting-Driven Development 45min
         * 19 01:45PM Clojure Ate Scala (on my project) 45min
         * 20 02:30PM A World Without HackerNews 30min
         * 21 03:00PM Ruby on Rails Legacy App Maintenance 60min
         * 22 04:00PM Rails for Python Developers lightning
         * 23 05:00PM Networking Event
         */

        /**
         * Track 1:
         * 09:00 AM 1 60min
         * 10:00 AM 2 45min
         * 10:45 AM 3 30min
         * 11:15 AM 4 45min
         * 12:00 PM Lunch
         * 01:00 PM 5 45min
         * 01:45 PM 6 lighting
         * 01:50 PM 7 60min
         * 02:50 PM 8 45min
         * 03:35 PM 9 30min
         * 04:05 PM 10 30min
         * 04:35 PM Network event
         *
         * Track 2:
         * 09:00 AM 11 45min
         * 09:45 AM 12 60min
         * 10:45 AM 13 60min
         * 12:00 PM Lunch
         * 01:00 PM 14 45min
         * 01:45 PM 15 30min
         * 02:15 PM 16 30min
         * 02:45 PM 17 60min
         * 03:45 PM 18 30min
         * 04:15 PM 19 30min
         * 04:45 PM Network event
         */
        System.out.println(nodes.stream().collect(Collectors.summingInt(Node::getDuration)));
        System.out.println(nodes.size());
        // 360 420

        //785 - 360 = 425
        //785 - 420 = 360



    }
//    static class Track {
//        List<Object> groups;
//    }
//    static class GroupA {
//        List<Node> nodes;
//    }
//
//    static class GroupB {
//        List<Node> nodes;
//    }
    static class Node {
        String name;
        int duration;

        public Node(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }


}
