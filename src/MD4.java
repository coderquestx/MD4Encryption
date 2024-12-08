public class MD4 {

    private int A, B, C, D;
    private int[] X = new int[16];
    private static final int[] S = { 3, 7, 11, 19 };

    public MD4() {
        reset();
    }

    public byte[] hash(byte[] input) {
        int[] res = calculateMD4(input);
        byte[] hashBytes = new byte[16];
        for (int i = 0; i < 4; i++) {
            hashBytes[i * 4] = (byte) (res[i] & 0xFF);
            hashBytes[i * 4 + 1] = (byte) ((res[i] >>> 8) & 0xFF);
            hashBytes[i * 4 + 2] = (byte) ((res[i] >>> 16) & 0xFF);
            hashBytes[i * 4 + 3] = (byte) ((res[i] >>> 24) & 0xFF);
        }
        return hashBytes;
    }

    private int[] calculateMD4(byte[] input) {
        reset();
        int len = input.length;
        int[] buf = new int[16];
        int j = 0;
        int i;

        for (i = 0; i < len; i++) {
            j = (i >>> 2) & 15;
            buf[j] = (buf[j] << 8) + (input[i] & 0xFF);
        }

        buf[j] = buf[j] << ((3 - (len & 3)) << 3);
        buf[14] = len << 3;
        process(buf);
        int[] result = new int[] { A, B, C, D };
        return result;
    }

    void reset() {
        A = 0x67452301;
        B = 0xefcdab89;
        C = 0x98badcfe;
        D = 0x10325476;
        for (int i = 0; i < X.length; i++) {
            X[i] = 0;
        }
    }


    private int F(int X, int Y, int Z) {
        return (X & Y) | (~X & Z);
    }

    private int G(int X, int Y, int Z) {
        return (X & Y) | (X & Z) | (Y & Z);
    }

    private int H(int X, int Y, int Z) {
        return X ^ Y ^ Z;
    }

    private int rotateLeft(int x, int n) {
        return (x << n) | (x >>> (32 - n));
    }

    private void FF(int a, int b, int c, int d, int x, int s) {
        a += F(b, c, d) + x;
        a = rotateLeft(a, s);
    }

    private void GG(int a, int b, int c, int d, int x, int s) {
        a += G(b, c, d) + x + 0x5a827999;
        a = rotateLeft(a, s);
    }

    private void HH(int a, int b, int c, int d, int x, int s) {
        a += H(b, c, d) + x + 0x6ed9eba1;
        a = rotateLeft(a, s);
    }

    private void process(int[] buf) {
        int AA, BB, CC, DD;
        for (int i = 0; i < 16; i++) {
            X[i] = buf[i];
        }
        AA = A;
        BB = B;
        CC = C;
        DD = D;
        FF(A, B, C, D, X[0], S[0]);
        FF(D, A, B, C, X[1], S[1]);
        FF(C, D, A, B, X[2], S[2]);
        FF(B, C, D, A, X[3], S[3]);
        FF(A, B, C, D, X[4], S[0]);
        FF(D, A, B, C, X[5], S[1]);
        FF(C, D, A, B, X[6], S[2]);
        FF(B, C, D, A, X[7], S[3]);
        FF(A, B, C, D, X[8], S[0]);
        FF(D, A, B, C, X[9], S[1]);
        FF(C, D, A, B, X[10], S[2]);
        FF(B, C, D, A, X[11], S[3]);
        FF(A, B, C, D, X[12], S[0]);
        FF(D, A, B, C, X[13], S[1]);
        FF(C, D, A, B, X[14], S[2]);
        FF(B, C, D, A, X[15], S[3]);
        GG(A, B, C, D, X[0], S[0]);
        GG(D, A, B, C, X[4], S[1]);
        GG(C, D, A, B, X[8], S[2]);
        GG(B, C, D, A, X[12], S[3]);
        GG(A, B, C, D, X[1], S[0]);
        GG(D, A, B, C, X[5], S[1]);
        GG(C, D, A, B, X[9], S[2]);
        GG(B, C, D, A, X[13], S[3]);
        GG(A, B, C, D, X[2], S[0]);
        GG(D, A, B, C, X[6], S[1]);
        GG(C, D, A, B, X[10], S[2]);
        GG(B, C, D, A, X[14], S[3]);
        GG(A, B, C, D, X[3], S[0]);
        GG(D, A, B, C, X[7], S[1]);
        GG(C, D, A, B, X[11], S[2]);
        GG(B, C, D, A, X[15], S[3]);
        HH(A, B, C, D, X[0], S[0]);
        HH(D, A, B, C, X[8], S[1]);
        HH(C, D, A, B, X[4], S[2]);
        HH(B, C, D, A, X[12], S[3]);
        HH(A, B, C, D, X[2], S[0]);
        HH(D, A, B, C, X[10], S[1]);
        HH(C, D, A, B, X[6], S[2]);
        HH(B, C, D, A, X[14], S[3]);
        HH(A, B, C, D, X[1], S[0]);
        HH(D, A, B, C, X[9], S[1]);
        HH(C, D, A, B, X[5], S[2]);
        HH(B, C, D, A, X[13], S[3]);
        HH(A, B, C, D, X[3], S[0]);
        HH(D, A, B, C, X[11], S[1]);
        HH(C, D, A, B, X[7], S[2]);
        HH(B, C, D, A, X[15], S[3]);
        A += AA;
        B += BB;
        C += CC;
        D += DD;
    }
}
