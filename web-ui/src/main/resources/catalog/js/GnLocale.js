(function() {
  goog.provide('gn_locale');
  goog.require('gn_cat_controller');

  var module = angular.module('gn_locale', [
    'pascalprecht.translate',
    'angular-md5',
    'gn_cat_controller'
  ]);

  module.constant('$LOCALES', ['core']);

  module.factory('localeLoader', ['$http', '$q', function($http, $q) {
    return function(options) {
      var allPromises = [];
      angular.forEach(options.locales, function(value, index) {
        var langUrl = options.prefix +
            options.key + '-' + value + options.suffix;

        var deferredInst = $q.defer();
        allPromises.push(deferredInst.promise);

        $http({
          method: 'GET',
          url: langUrl
        }).success(function(data) {
          deferredInst.resolve(data);
        }).error(function() {
          // Load english locale file if not available
          $http({
            method: 'GET',
            url: options.prefix +
                'en-' + value + options.suffix
          }).success(function(data) {
            deferredInst.resolve(data);
          }).error(function() {
            deferredInst.reject(options.key);
          });
        });
      });

      // Finally, create a single promise containing all the promises
      // for each app module:
      var deferred = $q.all(allPromises);
      return deferred;
    };
  }]);


  // TODO: could be improved instead of putting this in all main modules ?
  module.config(['$translateProvider', '$LOCALES', 'gnGlobalSettings',
    function($translateProvider, $LOCALES, gnGlobalSettings) {
      $translateProvider.useLoader('localeLoader', {
        locales: $LOCALES,
        prefix: (gnGlobalSettings.locale.path || '../../') + 'catalog/locales/',
        suffix: '.json'
      });

      gnGlobalSettings.lang = location.href.split('/')[5].substring(0, 2) || gnGlobalSettings.locale.lang || 'en';
      $translateProvider.preferredLanguage(gnGlobalSettings.lang);
      moment.lang(gnGlobalSettings.lang);
    }]);

})();
