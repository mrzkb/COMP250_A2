package assignment2;

/**
* Your name here: Mira Kandlikar-Bloch
* Your McGill ID here: 261035244
**/

public class SolitaireCipher {
    public Deck key;

    public SolitaireCipher (Deck key) {
	this.key = new Deck(key); // deep copy of the deck
    }

    /* 
     * TODO: Generates a keystream of the given size
     */
    public int[] getKeystream(int size) {
        int keyStream[]=new int[size];

        for(int i=0; i<size; i++){
            keyStream[i]=this.key.generateNextKeystreamValue();
        }
	return keyStream;
    }

    /* 
     * TODO: Encodes the input message using the algorithm described in the pdf.
     */
    public String encode(String msg) {
       String alphaOnly="";
       String encoded="";

        for(int i=0; i<msg.length(); i++){
            char c=msg.charAt(i);
            if(c>='a'&& c<='z'){
                c= (char) (c-32);
            }
            if (c>='A'&& c<='Z'){
                alphaOnly+=c;
            }
        }
        int keyStream[]=getKeystream(alphaOnly.length());
        for(int j=0; j<alphaOnly.length(); j++) {
            int value=keyStream[j];
            char ch= alphaOnly.charAt(j);
            encoded+=shiftRight(ch, value);

        }
	return encoded;
    }

    /* 
     * TODO: Decodes the input message using the algorithm described in the pdf.
     */
    public String decode(String msg) {
        String decoded="";
        int keyStream[]=getKeystream(msg.length());
        for(int i=0; i<msg.length(); i++){
            int value= keyStream[i];
            char ch=msg.charAt(i);
            decoded+=shiftLeft(ch,value);
        }


	return decoded;
    }

    private static char shiftRight(char ch, int value) {
        if (ch >= 'A' && ch <= 'Z') {
            ch= (char) (ch+32);
        }
        int pos = ch - 'a';
        int newPos = (pos + value) % 26;
        return (char) ('a' + newPos-32);
    }


    private static char shiftLeft(char ch, int value){
        if (ch >= 'A' && ch <= 'Z') {
            ch= (char) (ch+32);
        }
        int pos=ch-'z';

        int newPos=(pos-value)%26;
        return (char)('z'+newPos-32);
    }

}

