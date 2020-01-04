public class CesarCode {

    private final int A_ASCII_VALUE = 65;
    private final int Z_ASCII_VALUE = 90;
    private final int SPACE_ASCII_VALUE = 32;

    private int key;

    public CesarCode(int key) {
        this.key = keyValidation(key);
    }

    public void setKey(int key) {
        this.key = keyValidation(key);
    }


    public String encode(String textInput) {

        boolean[] inputCaseData = isUpperCase(textInput);
        int[] inputAsIntegers = convertStringToInt(textInput);
        int[] modifiedInputAsIntegers = getEncodedIntegers(inputAsIntegers);

        return convertIntegersToString(modifiedInputAsIntegers, inputCaseData);
    }

    public String decode(String textInput) {

        boolean[] inputCaseData = isUpperCase(textInput);
        int[] inputAsIntegers = convertStringToInt(textInput);
        int[] modifiedInputAsIntegers = getDecodedIntegers(inputAsIntegers);

        return convertIntegersToString(modifiedInputAsIntegers, inputCaseData);
    }

    private boolean[] isUpperCase(String txt) {
        boolean[] caseData = new boolean[txt.length()];

        for (int i = 0; i < txt.length(); i++) {

            if (Character.isUpperCase(txt.charAt(i))) {
                caseData[i] = true;
            } else {
                caseData[i] = false;
            }
        }
        return caseData;
    }

    private int[] convertStringToInt(String txt) {
        int[] asciiIntegers = new int[txt.length()];
        txt = txt.toUpperCase(); // this way there's no need to consider lowerCase letters
        for (int i = 0; i < txt.length(); i++) {
            asciiIntegers[i] = (int) txt.charAt(i);
        }
        return asciiIntegers;

    }

    private String convertIntegersToString(int[] modifiedInputAsIntegers, boolean[] inputCaseData) {
        StringBuilder sb = new StringBuilder();
        String letter;

        for (int i = 0; i < modifiedInputAsIntegers.length; i++) {
            letter = Character.toString(modifiedInputAsIntegers[i]);
            sb.append(letter);
        }

        return restoreUpperCases(sb.toString(), inputCaseData);

    }

    private String restoreUpperCases(String txt, boolean[] inputCaseData) {
        StringBuilder sb = new StringBuilder();
        Character letter;
        for (int i = 0; i < txt.length(); i++) {
            letter = txt.charAt(i);
            if (inputCaseData[i]) {
                sb.append(letter.toString().toUpperCase());
            } else {
                sb.append(letter.toString().toLowerCase());
            }
        }
        return sb.toString();
    }

    private int[] getEncodedIntegers(int[] inputAsIntegers) {

        if (key == 0) {
            return inputAsIntegers;
        }
        int[] encodedIntegers = new int[inputAsIntegers.length];
        key = keyValidation(key);

        for (int i = 0; i < inputAsIntegers.length; i++) {
            if (inputAsIntegers[i] == SPACE_ASCII_VALUE){
                continue;
            }
            if (inputAsIntegers[i] + key > 90) {
                encodedIntegers[i] = A_ASCII_VALUE + (inputAsIntegers[i] + key - Z_ASCII_VALUE - 1);
            } else {
                encodedIntegers[i] = inputAsIntegers[i] + key;
            }
        }
        return encodedIntegers;

    }

    private int[] getDecodedIntegers(int[] inputAsIntegers){

        if (key == 0) {
            return inputAsIntegers;
        }
        int[] encodedIntegers = new int[inputAsIntegers.length];
        key = keyValidation(key);

        for (int i = 0; i < inputAsIntegers.length; i++) {
            if (inputAsIntegers[i] == SPACE_ASCII_VALUE){
                continue;
            }
            if (inputAsIntegers[i] - key < 65){
                encodedIntegers[i] = Z_ASCII_VALUE - ((key - inputAsIntegers[i]) + A_ASCII_VALUE -1);
            }else{
                encodedIntegers[i] = inputAsIntegers[i] - key;
            }
        }
        return encodedIntegers;
    }

    private int keyValidation(int key) {
        if (key < 0) {
            return Math.abs(key);
        } else if (key > 26) {      // there are 26 letters
            return key % 26;
        } else {
            return key;
        }
    }

}
