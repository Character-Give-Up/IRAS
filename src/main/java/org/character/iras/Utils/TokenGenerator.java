package org.character.iras.Utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class TokenGenerator {
    private int digit;
    private final Random random = new Random(2023L);

    public TokenGenerator() {
    }

    public void setDigit(int digit){
        this.digit = digit;
    }

    public String generate(){
        StringBuilder builder = new StringBuilder();
        List<Character> characters = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            characters.add((char) i);
        }
        for (int i = 'a'; i <= 'z'; i++) {
            characters.add((char) i);
        }
        for (int i = '0'; i <= '9'; i++) {
            characters.add((char) i);
        }
        for (int i = 0; i < this.digit; i++) {
            Collections.shuffle(characters);
            builder.append(characters.get(random.nextInt(characters.size())));
        }
        return builder.toString();
    }


}
