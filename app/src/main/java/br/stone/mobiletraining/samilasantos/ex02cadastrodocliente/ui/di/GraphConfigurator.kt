package br.stone.mobiletraining.samilasantos.ex02cadastrodocliente.ui.di


open class GraphConfigurator {
    companion object {
        private var configurator: GraphConfigurator? = null

        fun switcher(newInstance: GraphConfigurator) {
            configurator = newInstance
        }

        fun getInstance(): GraphConfigurator {
            if (configurator == null) configurator = GraphConfigurator()

            return configurator as GraphConfigurator
        }
    }

    open fun entrepreneurInfoViewModelDependency(): EntrepreneurInfoViewModelInjector =
            EntrepreneurInfoViewModelInjector(repositoryDependency().inject())

    open fun entrepreneurListViewModelDependency(): EntrepreneurListViewModelInjector =
            EntrepreneurListViewModelInjector(repositoryDependency().inject())

    open fun registerViewModelDependency(): RegisterViewModelInjector =
            RegisterViewModelInjector(repositoryDependency().inject())

    open fun repositoryDependency(): RepositoryInjector =
            RepositoryInjector()

}
