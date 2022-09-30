package io.github.nginx.ops.server.system.domain.vo;

import com.baomidou.mybatisplus.annotation.OrderBy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 返回语句VO
 *
 * @author lihao3
 */
@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SysDictCacheVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 编码 */
    private String value;
    /** 名称 */
    private String label;
}
