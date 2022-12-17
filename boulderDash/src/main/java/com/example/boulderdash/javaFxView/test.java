//package com.example.boulderdash.javaFxView;
//
//public class test {
//    File file = new File("src/main/resources/level1.txt");
//    MainBox mainBox = new MainBox();
//    ControllerBoard board = new ControllerBoard(file);
//    CaveView cv = new CaveView(board.getBoard());
//    Scene scene = new Scene(mainBox);
//    Scene scene2 = new Scene(cv);
//
//        mainBox.getStartBtn().addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
//
//        @Override
//        public void handle(ActionEvent event) {
//
//            stage.setScene(scene2);
//
//        }
//    });
//
//            scene2.setOnKeyPressed(e -> {
//        switch (e.getCode()) {
//            case UP:
//                board.move("z");
//                cv.setBoard(board.getBoard());
//                cv.initialize();
//                break;
//            case DOWN:
//                board.move("s");
//                cv.setBoard(board.getBoard());
//                cv.initialize();
//                break;
//            case LEFT:
//                board.move("q");
//                cv.setBoard(board.getBoard());
//                cv.initialize();
//                break;
//            case RIGHT:
//                board.move("d");
//                cv.setBoard(board.getBoard());
//                cv.initialize();
//                break;
//        }
//    });
//
//        stage.setTitle("Bolder Dash");
//        stage.setScene(scene);
//        stage.show();
//
//
//}
