package rzgonz.id.guru.apps

import rzgonz.core.kotlin.apps.RzApps
import rzgonz.core.kotlin.helper.APIHelper

/**
 * Created by rzgonz on 7/12/17.
 */
class APKModel : RzApps {

    constructor() : super()

    init {
        APIHelper.BASE_URL = "https://opentdb.com/"
    }


}
