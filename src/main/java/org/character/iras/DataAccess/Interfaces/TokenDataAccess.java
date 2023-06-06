package org.character.iras.DataAccess.Interfaces;

import jakarta.annotation.Nullable;
import org.character.iras.Entity.Token;

public interface TokenDataAccess extends DataAccess{

    /**
     * 通过令牌值寻找令牌
     * @param value 令牌的值
     * @return 令牌实体
     */
    @Nullable
    Token getTokenByValue(String value);

    void addToken(String value);

}
