var emailRegex = '^[a-zA-Z0-9.!#$%&\'*+\/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)*$';
var passwordRegex = '^(?=.*[A-Za-z])(?=.*\\d)(?=.*[ -!@#$%^&*()+|~=`{}\\[\\]:";\'<>?\\/])[A-Za-z\\d -!@#$%^&*()+|~=`{}\\[\\]:";\'<>?\\/]{6,20}$';
var passwordUserRegex = '^((?=.*[a-zA-Z].*)(?=.*\\d)|(?=.*[a-zA-Z])(?=.*[ -!@\\-_#$%^&*()+|~={}\\[\\]:";\'<>?\\/])|(?=.*\\d)(?=.*[ -!@\\-_#$%^&*()+|~={}\\[\\]:";\'<>?\\/]))[a-zA-Z\\d -!@\\-_#$%^&*()+|~={}\\[\\]:";\'<>?\\/]{6,20}$';
//full-width KATAKANA
var kanaRegex = '^[ァ-・ヽヾ゛゜ー]+$';
// full-width KATAKANA & KANJI
var kanaKanjiRegex = '^[ァ-ン一-龥]+$';
// Half-width number with NO hyphen ( - )
var halfwidthNumberRegex = '^[0-9]+$';
// full-width HIRAGANA & full-width KATAKANA & KANJI
var japaneseFullwidthRegex = '^[ーぁ-んァ-ン一-龥－]+$';
// full-width number && full-width HIRAGANA & full-width KATAKANA & KANJI with hyphen (-): 市区町村・番地 && マンション名・建物名・部屋番号
var addressRegex = '^[ー－０-９Ａ-Ｚａ-ｚぁ-んァ-ン一-龥　]+$';
var postCodeRegex = '\\d{7}';
var validateMessages = {
    confirmPassword: {
        pattern: 'パスワードとパスワード（確認）が一致していません。'
    },
    confirmMailAddress: {
        pattern: 'メールアドレスが一致していません。'
    }
};

$(function () {
    $('[data-toggle="tooltip"]').tooltip()
});

function addPatternAttr(elementId, regex) {
    if ($('#' + elementId).length) {
        $('#' + elementId).attr('pattern', regex);
    }
}

function numbericOnlyPaste(elementId, isFloat) {
    var regex = isFloat ? /[^0-9.]/g : /[^0-9]/g;
    var element = $('#' + elementId);
    element.on('focusout', function (){
        element.val(element.val().replace(regex, ''));
    }).on('paste', function () {
        setTimeout(function () {
            element.val(element.val().replace(regex, ''));
        }, 5);
    });
}

/**
 * @param event
 */
function numericOnly(event, isFloat) {
    if ([46, 8, 9, 27, 13].indexOf(event.keyCode) > -1 ||
        // Allow: Ctrl+A
        (event.keyCode === 65 && (event.ctrlKey || event.metaKey)) ||
        // Allow: Ctrl+C
        (event.keyCode === 67 && (event.ctrlKey || event.metaKey)) ||
        // Allow: Ctrl+V
        (event.keyCode === 86 && (event.ctrlKey || event.metaKey)) ||
        // Allow: Ctrl+X
        (event.keyCode === 88 && (event.ctrlKey || event.metaKey)) ||
        // Allow: home, end, left, right
        (event.keyCode >= 35 && event.keyCode <= 39) || (isFloat && (event.keyCode === 190 || event.keyCode === 110))) {
        // let it happen, don't do anything
        return;
    }
    // Ensure that it is a number and stop the keypress
    if ((event.shiftKey || (event.keyCode < 48 || event.keyCode > 57)) && (event.keyCode < 96 || event.keyCode > 105)) {
        event.preventDefault();
    }
}

/**
 * Returns a function, that, as long as it continues to be invoked, will not
 * be triggered. The function will be called after it stops being called for
 * N milliseconds. If `immediate` is passed, trigger the function on the
 * leading edge, instead of the trailing.
 */
function debounce(func, wait, immediate) {
    var timeout;
    return function () {
        var context = this, args = arguments;
        var later = function () {
            timeout = null;
            if (!immediate) {
                func.apply(context, args);
            }
        };
        var callNow = immediate && !timeout;
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
        if (callNow) {
            func.apply(context, args);
        }
    };
};

/**
 * Limit client to one submission per time period
 * @param form
 * @param wait
 * @param immediate
 */
function limitSubmission(form, wait, immediate) {
    var submitDebounce = debounce(onSubmit, wait, immediate);
    $('button[type="submit"]').on('click', function(event){
        event.preventDefault();
        event.stopPropagation();
        this.click(null);
        if (form.checkValidity()) {
            submitDebounce();
        }
        form.classList.add('was-validated');
    });
    function onSubmit() {
        form.submit()
    }
}

/** Validate password and confirm password
 * @param passwordId
 * @param confirmPasswordId
 * @returns {boolean}
 */
function checkConfirmPassword(passwordId, confirmPasswordId) {
    var isMatch = true;
    var passwordElement = $("#" + passwordId);
    var password = passwordElement.val();
    var confirmPasswordElement = $("#" + confirmPasswordId);
    var confirmPassword = confirmPasswordElement.val();
    confirmPasswordElement.get(0).setCustomValidity('');
    if (passwordElement.get(0).validity.valid && confirmPasswordElement.get(0).validity.valid &&
        password !== confirmPassword) {
        isMatch = false;
        confirmPasswordElement.get(0).setCustomValidity(validateMessages.confirmPassword.pattern);
    }
    return isMatch;
}

function validatePassword(p1, p2) {
    if (p1.value !== p2.value && p2.value !== '') {
        p2.setCustomValidity(validateMessages.confirmPassword.pattern);
    } else {
        p2.setCustomValidity('');
    }
}

// add validation message
function addFeedback(message) {
    return '<div class="invalid-message">' + message +  '</div>';
}
