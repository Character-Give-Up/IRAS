package org.character.iras.DataAccess.Interfaces;

import jakarta.annotation.Nullable;
import org.character.iras.Entity.Token;

/**
 * 令牌数据连接器接口
 */
public interface TokenDataAccess extends DataAccess{

    /**
     * 通过令牌值寻找令牌
     * @param value 令牌的值
     * @return 令牌实体
     * @apiNote 返回值可空：如果找不到令牌，则返回<code>null</code>
     */
    @Nullable
    Token getTokenByValue(String value);
    /**
     * 新增一个令牌
     * @param value 令牌值
     */
    void addToken(String value);

}
