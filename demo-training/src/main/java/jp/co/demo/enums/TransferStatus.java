package jp.co.demo.enums;

import jp.co.demo.common.StringConst;
import org.apache.commons.lang3.StringUtils;

/**
 * TransferStatus
 */
public enum TransferStatus {

    NEW(StringConst.TRANSFER_STATUS_NEW, StringConst.ZERO),
    COPYING(StringConst.TRANSFER_STATUS_COPYING, StringConst.ONE),
    SENDING(StringConst.TRANSFER_STATUS_SENDING, StringConst.TWO),
    DONE(StringConst.TRANSFER_STATUS_DONE, StringConst.THREE),
    ERROR(StringConst.TRANSFER_STATUS_ERROR, StringConst.FOUR),
    RESEND(StringConst.TRANSFER_STATUS_RESEND, StringConst.FIVE);

    private String text;
    private String value;

    TransferStatus(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public static TransferStatus getStatus(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        switch (value) {
            case StringConst.ZERO:
                return NEW;
            case StringConst.ONE:
                return COPYING;
            case StringConst.TWO:
                return SENDING;
            case StringConst.THREE:
                return DONE;
            case StringConst.FOUR:
                return ERROR;
            case StringConst.FIVE:
                return RESEND;
            default:
                return null;
        }
    }
}
