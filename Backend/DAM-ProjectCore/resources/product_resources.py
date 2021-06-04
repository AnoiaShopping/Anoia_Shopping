import falcon

from sqlalchemy.exc import IntegrityError
from resources.base_resources import DAMCoreResource
from hooks import requires_auth
from db.models import Product

#@falcon.before(requires_auth)
class ResourcesCreateProduct(DAMCoreResource):
    def on_post(self, req, resp, *args, **kwargs):
        super(ResourceCreateProduct, self).on_post(req, resp, *args, **kwargs)
        
        product = Product()
        
        try:
            product.description =  req.media["description"]
            product.name = req.media["name"]
            self.db_session.add(product)
            
            try:
                self.db_session.commit()
            except IntegrityError:
                raise falcon.HTTPBadRequest("")#NO CAL EL TRY CATCH AQUEST JA QUE EL NOM NO ES ÚNIC
                   
        except KeyError:
            raise falcon.HTTPBadRequest("Per poder donar d'alta un producte cal un nom i una descripció al body de la petició")
            
        resp.status = falcon.HTTP_200
        
        
class ResourcesFindProductByOwner(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourcesFindProductByOwner, self).on_get(req, resp, *args, **kwargs)
        
        owner = req.media[owner]
        if owner is not None:
            product = self.db_session.query(Product).filter(Product.owner == owner).one_or_none()
            if product is not None:
                resp.media = product.json_model #enviem 
            else:
                raise falcon.HTTPBadRequest(
                "No existeixen")
        else:
            raise falcon.HTTPBadRequest(
                "Per buscar els productes cal saber un propietari de botiga")
        resp.status = falcon.HTTP_200
        
        
class ResourcesGetProducts(DAMCoreResource):
    def on_get(self, req, resp, *args, **kwargs):
        super(ResourcesGetProducts, self).on_get(req, resp, *args, **kwargs)
        
        products = list()
        business_id = req.get_param("business_id")
        
        if business_id is not None:
            query = self.db_session.query(Product).filter(Product.owner_id == business_id)
            results = query.all()
        
            for product in results:
                products.append(product.json_model)
        
        resp.media = products
      