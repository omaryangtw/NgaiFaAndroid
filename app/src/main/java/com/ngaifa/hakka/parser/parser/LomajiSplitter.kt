package com.ngaifa.hakka.parser.parser

object LomajiSplitter {

    //private const val TAILO_UNICODE_REGEX = "(ph|p|m|b|th|tsh|ts|t|n|l|kh|k|ng|g|h|s|j)?(([áàâāa̍a̋aíìîīi̍i̋iúùûūu̍űuéèêēe̍e̋eóòôōo̍őo]+(nn|ńg|ǹg|n̂g|n̄g|n̍g|n̋g|ng|ń|ǹ|n̂|n̄|n̍|n̋|n|ḿ|m̀|m̂|m̄|m̍|m̋|m)?)|(nn|ńg|ǹg|n̂g|n̄g|n̍g|n̋g|ng|ń|ǹ|n̂|n̄|n̍|n̋|n|ḿ|m̀|m̂|m̄|m̍|m̋|m))(p|t|k|h)?"
    //private const val POJ_UNICODE_REGEX = "(ph|p|m|b|th|chh|ch|t|n|l|kh|k|ng|g|h|s|j)?(([áàâāa̍ăaíìîīi̍ĭiúùûūu̍ŭuéèêēe̍ĕeó͘ò͘ô͘ō͘o̍͘ǒ͘o͘óòôōo̍ŏo]+(ⁿ|ńg|ǹg|n̂g|n̄g|n̍g|n̆g|ng|ń|ǹ|n̂|n̄|n̍|n̆|n|ḿ|m̀|m̂|m̄|m̍|m̆|m)?)|(ⁿ|ńg|ǹg|n̂g|n̄g|n̍g|n̆g|ng|ń|ǹ|n̂|n̄|n̍|n̆|n|ḿ|m̀|m̂|m̄|m̍|m̆|m))(p|t|k|h)?"
    private const val TAILO_UNICODE_REGEX = "(b|p|m|f|v|d|t|n|l|z|j|c|q|s|x|g|k|ng|h)?((([iueoa])+(m|n|ng)?)|(m|n|ng))([bdg])?([ˊˇˋ])?"
    private const val POJ_UNICODE_REGEX = "(p|ph|m|f|t|th|n|l|ch|chh|s|k|kh|ng|h)?(([âàáaa̍êèéee̍îìíii̍ôòóoo̍ṳ̂ṳ̀ṳ́ṳṳ̍ûùúuu̍]+(ńg|ǹg|n̂g|n̄g|n̍g|n̆g|ng|ń|ǹ|n̂|n̄|n̍|n̆|n|ḿ|m̀|m̂|m̄|m̍|m̆|m)?)|(ńg|ǹg|n̂g|n̄g|n̍g|n̆g|ng|ń|ǹ|n̂|n̄|n̍|n̆|n|ḿ|m̀|m̂|m̄|m̍|m̆|m))([ptk])?"
    fun splitLomajiSoojiTiauho(str: String): Sequence<MatchResult> {
        return Regex("[a-zA-Z]+([1-9])?").findAll(str)
    }
    fun splitTailoUnicode(str: String): Sequence<MatchResult> {
        return Regex(TAILO_UNICODE_REGEX, RegexOption.IGNORE_CASE).findAll(str)
    }

    fun splitPojUnicode(str: String): Sequence<MatchResult> {
        return Regex(POJ_UNICODE_REGEX, RegexOption.IGNORE_CASE).findAll(str)
    }
/*
    fun splitMoeUnicode(str: String): Sequence<MatchResult> {
        return Regex(MOE_UNICODE_REGEX, RegexOption.IGNORE_CASE).findAll(str)
    }

    fun splitPFSUnicode(str: String): Sequence<MatchResult> {
        return Regex(PFS_UNICODE_REGEX, RegexOption.IGNORE_CASE).findAll(str)
    }

 */
}
