# Quick Android Examples

    typealias OnSuccessListener<T> = (T) -> Unit
    typealias OnErrorListener = (Exception) -> Unit

    private val logTag by lazy { "App: " + this.javaClass.simpleName }
    fun log(message: String) {
        Log.d(logTag, message)
    }

### ViewModel

    /** Example implementation of a ViewModel. The error handling could be better by providing a good message, rather than null. */
    class BusinessDetailsViewModel : ViewModel() {

        private val business = MutableLiveData<Business?>()

        fun getBusiness(): LiveData<Business?> = business

        fun updateBusiness(name: String, email: String) {
            Repo.getBusiness(name, email,
                onSuccess = { business.postValue(it) },
                onError = { business.postValue(null) }
            )
        }

    }

    // Usage
    private val viewModel by lazy { ViewModelProviders.of(this).get(BusinessDetailsViewModel::class.java) }
    viewModel.getBusiness().observe(this, Observer { business ->
        updateView(business)
    })




