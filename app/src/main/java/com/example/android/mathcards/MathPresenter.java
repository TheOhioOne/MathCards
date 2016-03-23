package com.example.android.mathcards;

public class MathPresenter {
    public int getRandomNumber(){
        return (int)Math.round(Math.random() * 10);
    }

    public int sumNumbers(int a, int b){
        return a + b;
    }

    public int subtractNumbers(int a, int b){
        return a - b;
    }

    public int multiplyNumbers(int a, int b){
        return a * b;
    }

    public int divideNumbers(int a, int b){
        return a / b;
    }

    public int squareNumber(int a){
        return (int) Math.pow(a,2);
    }

    public int createNewNumbers(int topNumber,int bottomNumber, Mode mode){
        if (mode == Mode.add){
            return sumNumbers(topNumber,bottomNumber);
        }
        if (mode == Mode.subtract){
            return subtractNumbers(topNumber,bottomNumber);
        }
        if (mode == Mode.multiply){
            return multiplyNumbers(topNumber,bottomNumber);
        }
        if (mode == Mode.divide){
            return multiplyNumbers(topNumber, bottomNumber);

        }
        if (mode == Mode.square){
            return squareNumber(bottomNumber);
        }

        return 0;
    }
}
